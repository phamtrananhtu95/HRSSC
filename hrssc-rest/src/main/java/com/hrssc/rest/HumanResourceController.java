package com.hrssc.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.HumanResourceSkillDTO;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Skill;
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
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HumanResourceDto> getHumanResource() {
		return humanResourceService.getHumanResources();
	}
	
	@GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HumanResourceDto getHumanResourceById(@PathVariable int id) throws NotFoundException {
		return humanResourceService.getHumanResourceById(id);
	}

	@JsonView(HumanResourceView.overview.class)
	@GetMapping(value = "/get/{managerID}")
	public List<HumanResource> getHumanResourceByManagerId(@PathVariable("managerID") int managerId){
		return humanResourceService.getHumanResourceByManagerId(managerId);
	}

	@PostMapping(value = "/add")
	public String addHumanResource(@RequestBody HumanResource humanresource){
		humanResourceService.addHumanResource(humanresource);
		return "OK";
	}

	@PostMapping(value = "/update")
	public String updateHumanResource(@RequestBody HumanResource humanResource){
		humanResourceService.updateHumanResource(humanResource);
		return "OK";
	}
}