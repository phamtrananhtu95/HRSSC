package com.hrssc.rest;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.Constant;
import com.hrssc.domain.dto.HumanResourceSkillDTO;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Job;
import com.hrssc.entities.Skill;
import com.hrssc.entities.User;
import com.hrssc.service.AuthorizationService;
import com.hrssc.service.MatchingService;
import com.hrssc.service.SimilarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.hrssc.domain.dto.HumanResourceDto;
import com.hrssc.service.HumanResourceService;

import javassist.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/humanResource")
public class HumanResourceController {

	@Autowired
	private HumanResourceService humanResourceService;

	@Autowired
	private MatchingService matchingService;

	@Autowired
	private SimilarService similarService;

	@Autowired
	AuthorizationService authorizationService;



	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HumanResourceDto> getHumanResource() {
		return humanResourceService.getHumanResources();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HumanResourceDto getHumanResourceById(@PathVariable int id) throws NotFoundException {
		return humanResourceService.getHumanResourceById(id);
	}

	@JsonView(HumanResourceView.overview.class)
	@GetMapping(value = "/get/{managerID}")
	public List<HumanResource> getHumanResourceByManagerId(@PathVariable("managerID") int managerId) {
		return humanResourceService.getHumanResourceByManagerId(managerId);
	}

	@JsonView(HumanResourceView.overview.class)
	@GetMapping(value = "/get-appliable/{userId}/{projectId}")
	public List<HumanResource> getAvailableResourceByManagerId(@PathVariable("projectId") int projectId,
															   @PathVariable("userId") int userId){
		return humanResourceService.getAppliableResourceById(projectId,userId);
	}

	@PostMapping(value = "/add")
	public ResponseStatus addHumanResource(@RequestBody HumanResource humanResource) {
		ResponseStatus response = new ResponseStatus(humanResourceService.addHumanResource(humanResource));
		humanResource = humanResourceService.getHumanResourceByEmail(humanResource.getEmail());
		if(humanResource.getStatus() == Constant.ResourceStatus.AVAILABLE){
			matchingService.matchResource(humanResource.getId());
			similarService.findSimilarResource(humanResource.getId());
		}
		return response;
	}

	@PostMapping(value = "/update")
	public ResponseStatus updateHumanResource(@RequestBody HumanResource humanResource) {
		ResponseStatus response = new ResponseStatus(humanResourceService.updateHumanResource(humanResource));


		if(humanResource.getStatus() == Constant.ResourceStatus.AVAILABLE){

			matchingService.matchResource(humanResource.getId());
			similarService.findSimilarResource(humanResource.getId());
		}

		return  response;
	}
	@PostMapping(value = "/change-status")
	public ResponseStatus changeResourceStatus(@RequestBody HumanResource humanResource){
		return new ResponseStatus(humanResourceService.changeResourceStatus(humanResource));
	}

	@JsonView(HumanResourceView.details.class)
	@GetMapping(value = "/details/{userId}/{resourceId}")
	public HumanResource viewHumanResourceDetails(@PathVariable("resourceId") int resourceId,
												  @PathVariable("userId") int userId){
		if(!authorizationService.checkResource(resourceId,userId)){
			return null;
		}
		return humanResourceService.viewHumanResourceDetails(resourceId);
	}

	@JsonView(HumanResourceView.history.class)
	@GetMapping(value = "/history/{resourceId}")
	public List<Job> viewHumanresourceHistory(@PathVariable("resourceId") int id){
		return humanResourceService.viewHumanresourceHistory(id);
	}
}


