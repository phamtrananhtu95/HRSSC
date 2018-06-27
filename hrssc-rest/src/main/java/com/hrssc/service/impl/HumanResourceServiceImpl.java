package com.hrssc.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.springframework.transaction.annotation.Transactional;

@Service
public class HumanResourceServiceImpl implements HumanResourceService{
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

	@Transactional
	@Override
	public String addHumanResource(HumanResource humanResourceSkill) {
		try {
			HumanResource humanResource = new HumanResource();
			humanResource.setFullname(humanResourceSkill.getFullname());
			humanResource.setStatus(humanResourceSkill.getStatus());
			humanResource.setEmail(humanResourceSkill.getEmail());
			humanResource.setTel(humanResourceSkill.getTel());
			humanResource.setAvailableDate(humanResourceSkill.getAvailableDate());
			humanResource.setAvailableDuration(humanResourceSkill.getAvailableDuration());
			humanResource.setCompanyId(humanResourceSkill.getCompanyId());
			humanResource.setUserId(humanResourceSkill.getUserId());

			humanResourceRepository.save(humanResource);

			HumanResource hm = humanResourceRepository.findByEmail(humanResourceSkill.getEmail());

			List<ResourceSkills> resourceSkillsList = (List<ResourceSkills>) humanResourceSkill.getResourceSkillsById();
			for (ResourceSkills tmp : resourceSkillsList) {
//			ResourceSkills resourceSkills = new ResourceSkills();
//			resourceSkills.setHumanResourceId(hm.getId());
//			resourceSkills.setSkillId(x.getSkillId());
//			resourceSkills.setExperience(x.getExperience());
				tmp.setHumanResourceId(hm.getId());
				resourceSkillRepository.save(tmp);
			}
			return "Successfully update resource.";
		}catch (RuntimeException e){
			Logger.getLogger(HumanResourceService.class.getName()).log(Level.INFO,e.toString());
			return "Error Occurred, Update has failed.";
		}
	}

	@Transactional
	@Override
	public String updateHumanResource(HumanResource humanResource) {
		Optional<HumanResource> hmResource = humanResourceRepository.findById(humanResource.getId());

		try {
			if (hmResource.isPresent()) {
				HumanResource resourceUpdate = hmResource.get();
				resourceUpdate.setFullname(humanResource.getFullname());
				resourceUpdate.setStatus(humanResource.getStatus());
				resourceUpdate.setEmail(humanResource.getEmail());
				resourceUpdate.setTel(humanResource.getTel());
				resourceUpdate.setAvailableDate(humanResource.getAvailableDate());
				resourceUpdate.setAvailableDuration(humanResource.getAvailableDuration());
//			resourceUpdate.setResourceSkillsById(humanResource.getResourceSkillsById());
				humanResourceRepository.save(resourceUpdate);


				List<ResourceSkills> list = resourceSkillRepository.getResourceSkillsByHumanResourceId(hmResource.get().getId());

				for (ResourceSkills x : list) {
					resourceSkillRepository.delete(x);
				}
				List<ResourceSkills> resourceSkillsList = (List<ResourceSkills>) humanResource.getResourceSkillsById();
				for (ResourceSkills tmp : resourceSkillsList) {

					tmp.setHumanResourceId(humanResource.getId());
					resourceSkillRepository.save(tmp);
				}
				return "Successfully update resource.";
			}
			return "Resource not existed.";
		}catch (RuntimeException e){
			Logger.getLogger(HumanResourceService.class.getName()).log(Level.INFO,e.toString());
			return "Error Occurred, Update has failed.";
		}
	}
}
