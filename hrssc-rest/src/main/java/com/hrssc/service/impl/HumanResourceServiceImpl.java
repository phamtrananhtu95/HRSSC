package com.hrssc.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.hrssc.domain.dto.HumanResourceSkillDTO;
import com.hrssc.entities.ResourceSkills;
import com.hrssc.entities.Skill;
import com.hrssc.repository.ResourceSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.HumanResourceDto;
import com.hrssc.entities.HumanResource;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.service.HumanResourceService;

import javassist.NotFoundException;

@Service
public class HumanResourceServiceImpl implements HumanResourceService {
	@Autowired
	private HumanResourceRepository humanResourceRepository;

	@Autowired
	private ResourceSkillRepository resourceSkillRepository;

	@Override
	public List<HumanResourceDto> getHumanResources() {
		List<HumanResourceDto> employeeList = new ArrayList<>();
		List<HumanResource> humanResources = humanResourceRepository.findAll();
		humanResources.stream().forEach(item -> {
			employeeList.add(new HumanResourceDto().getHumanResource(item));
		});

		return employeeList;
	}

	@Override
	public HumanResourceDto getHumanResourceById(int id) throws NotFoundException {
		Optional<HumanResource> humanResource = humanResourceRepository.findById(id);

		if (!humanResource.isPresent()) {
			throw new NotFoundException("not found human resource with id ...");
		}

		return new HumanResourceDto().getHumanResource(humanResource.get());
	}

	@Override
	public List<HumanResource> getHumanResourceByManagerId(int managerId) {
		return humanResourceRepository.getHumanResourcesByUserId(managerId);
	}

	@Override
	public void addHumanResource(HumanResource humanResourceSkill) {

		HumanResource humanResource = new HumanResource();
		humanResource.setFullname(humanResourceSkill.getFullname());
		humanResource.setStatus(humanResourceSkill.getStatus());
		humanResource.setEmail(humanResourceSkill.getEmail());
		humanResource.setTel(humanResourceSkill.getTel());
		humanResource.setAvailableDate(humanResourceSkill.getAvailableDate());
		humanResource.setAvailableDuration(humanResourceSkill.getAvailableDuration());
		humanResource.setCompanyId(humanResourceSkill.getCompanyId());
		humanResource.setPositionId(humanResourceSkill.getPositionId());
		humanResource.setUserId(humanResourceSkill.getUserId());

//
		humanResourceRepository.save(humanResource);

		HumanResource hm = humanResourceRepository.findByEmail(humanResourceSkill.getEmail());

		List<ResourceSkills> tmp2 = (List<ResourceSkills>) humanResourceSkill.getResourceSkillsById();
		System.out.println("");
		for ( ResourceSkills x: tmp2) {
//			ResourceSkills resourceSkills = new ResourceSkills();
//			resourceSkills.setHumanResourceId(hm.getId());
//			resourceSkills.setSkillId(x.getSkillId());
//			resourceSkills.setExperience(x.getExperience());
			x.setHumanResourceId(hm.getId());
			resourceSkillRepository.save(x);
		}
	}
}
