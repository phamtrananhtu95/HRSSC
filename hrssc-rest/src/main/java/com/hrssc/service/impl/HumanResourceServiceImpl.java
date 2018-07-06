package com.hrssc.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hrssc.domain.Constant;
import com.hrssc.domain.dto.HumanResourceSkillDTO;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.HumanResourceDto;
import com.hrssc.service.HumanResourceService;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HumanResourceServiceImpl implements HumanResourceService{
	@Autowired
	private HumanResourceRepository humanResourceRepository;

	@Autowired
	private ResourceSkillRepository resourceSkillRepository;

	@Autowired
	InteractionRepository interactionRepository;

	@Autowired
	JobRepository jobRepository;

	@Autowired
	UserRepository userRepository;

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
	public List<HumanResource> getAppliableResourceById(int projectId) {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> userOptional = userRepository.findByUsername(authenticatedUser.getName());
		if(!userOptional.isPresent()){
			return null;
		}
		User user = userOptional.get();
		List<HumanResource> resourceList = humanResourceRepository.findByUserIdAndStatus(user.getId(),Constant.ResourceStatus.AVAILABLE);
		if(resourceList == null){
			return null;
		}
		List<HumanResource> resultList = new ArrayList<>();
		for(HumanResource resource:resourceList){
			//Nếu interaction list null thì có nghĩa là resource này chưa tương tác với bất kì project nào
			//kể cả project này, Thêm resource này vào resultList
			if(resource.getInteractionsById() == null){
				resultList.add(resource);

			}else {
				boolean addable = true;
				for (Interaction interaction : resource.getInteractionsById()) {
					if (interaction.getProjectId() == projectId) {
						if (interaction.getType().equals(Constant.InteractionType.INVITE) ||
								interaction.getType().equals(Constant.InteractionType.APPLY)) {
							resultList.remove(resource);
							addable = false;
							break;
						}

					}

				}
				if (addable) {
					resultList.add(resource);

				}
			}
		}
		return resultList;
	}

	@Transactional
	@Override
	public String addHumanResource(HumanResource humanResourceSkill) {
		try {
			HumanResource humanResource = new HumanResource();
			humanResource.setFullname(humanResourceSkill.getFullname());
			humanResource.setStatus(Constant.ResourceStatus.INACTIVE);
			humanResource.setEmail(humanResourceSkill.getEmail());
			humanResource.setTel(humanResourceSkill.getTel());
			humanResource.setAvailableDate(humanResourceSkill.getAvailableDate());
			humanResource.setAvailableDuration(humanResourceSkill.getAvailableDuration());
			humanResource.setCompanyId(humanResourceSkill.getCompanyId());
			humanResource.setUserId(humanResourceSkill.getUserId());
			humanResource.setSalary(humanResourceSkill.getSalary());

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
			return "Successfully add resource.";
		}catch (IncorrectResultSizeDataAccessException e){
			Logger.getLogger(HumanResourceService.class.getName()).log(Level.INFO,e.toString());
			return "Email existed.";
		}catch(RuntimeException e){
			Logger.getLogger(HumanResourceService.class.getName()).log(Level.INFO,e.toString());
			return "Error Occurred, Failed to add resource.";
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
				resourceUpdate.setSalary(humanResource.getSalary());
//			resourceUpdate.setResourceSkillsById(humanResource.getResourceSkillsById());
				resourceUpdate = humanResourceRepository.save(resourceUpdate);


				List<ResourceSkills> list = resourceSkillRepository.getResourceSkillsByHumanResourceId(hmResource.get().getId());

				for (ResourceSkills x : list) {
					resourceSkillRepository.delete(x);
				}
				List<ResourceSkills> resourceSkillsList = (List<ResourceSkills>) humanResource.getResourceSkillsById();
				for (ResourceSkills tmp : resourceSkillsList) {

					tmp.setHumanResourceId(humanResource.getId());
					resourceSkillRepository.save(tmp);
				}

				//Nếu chuyển trạng thái từ Available sang INACTIVE hoặc BUSY thì phải xóa hết các MATCHING thuộc resource này
				if(resourceUpdate.getStatus() == Constant.ResourceStatus.INACTIVE ||
						resourceUpdate.getStatus() == Constant.ResourceStatus.BUSY){
					interactionRepository.deleteByHumanResourceIdAndType(humanResource.getId(),Constant.InteractionType.MATCH);
				}

				return "Successfully update resource.";
			}
			return "Resource not existed.";
		}catch (RuntimeException e){
			Logger.getLogger(HumanResourceService.class.getName()).log(Level.INFO,e.toString());
			return "Error Occurred, Update has failed.";
		}
	}

	@Override
	public  HumanResource viewHumanResourceDetails(int id){
		Optional<HumanResource> humanResource = humanResourceRepository.findById(id);
		if (humanResource.isPresent()) {
			return humanResource.get();
		}
		return null;
	}

	@Override
	public String changeResourceStatus(HumanResource humanResource){
		Optional<HumanResource> resourceOpt = humanResourceRepository.findById(humanResource.getId());
		if(resourceOpt.isPresent()){
			HumanResource resource = resourceOpt.get();
			if(humanResource.getStatus() == Constant.ResourceStatus.AVAILABLE){
				resource.setStatus(Constant.ResourceStatus.AVAILABLE);
			}
			if(humanResource.getStatus() == Constant.ResourceStatus.INACTIVE){
				resource.setStatus(Constant.ResourceStatus.INACTIVE);
			}
			if(humanResource.getStatus() == Constant.ResourceStatus.BUSY){
				resource.setStatus(Constant.ResourceStatus.BUSY);
			}
			humanResourceRepository.save(resource);
			return "OK";
		}
		return "Resource not found";
	}

	public List<Job> viewHumanresourceHistory(int id){
		List<Job> resultJob = jobRepository.findByHumanResourceIdAndStatus(id, Constant.JobStatus.FINISHED);
		return resultJob;
	}
}
