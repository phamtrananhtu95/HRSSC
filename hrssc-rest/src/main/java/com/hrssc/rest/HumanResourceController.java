package com.hrssc.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.HumanResourceSkillDTO;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Skill;
import com.hrssc.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.hrssc.domain.dto.HumanResourceDto;
import com.hrssc.service.HumanResourceService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/humanResource")
public class HumanResourceController {

	@Autowired
	private HumanResourceService humanResourceService;

	@Autowired
	private MatchingService matchingService;

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

	@PostMapping(value = "/add")
	public ResponseStatus addHumanResource(@RequestBody HumanResource humanresource) {
		return new ResponseStatus(humanResourceService.addHumanResource(humanresource));
	}

	@PostMapping(value = "/update")
	public ResponseStatus updateHumanResource(@RequestBody HumanResource humanResource) {
		ResponseStatus response = new ResponseStatus(humanResourceService.updateHumanResource(humanResource));
		matchingService.matchResource(humanResource.getId());
		return  response;
	}
	@PostMapping(value = "/change-status")
	public ResponseStatus changeResourceStatus(@RequestBody HumanResource humanResource){
		return new ResponseStatus(humanResourceService.changeResourceStatus(humanResource));
	}

	@JsonView(HumanResourceView.details.class)
	@GetMapping(value = "/details/{id}")
	public HumanResource viewHumanResourceDetails(@PathVariable("id") int id) throws NotFoundException {
		return humanResourceService.viewHumanResourceDetails(id);
	}
}


