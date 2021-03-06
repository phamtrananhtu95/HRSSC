package com.hrssc.service;

import java.util.List;

import com.hrssc.domain.dto.HumanResourceDto;

import com.hrssc.domain.dto.HumanResourceSkillDTO;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Job;
import com.hrssc.entities.Skill;
import javassist.NotFoundException;



public interface HumanResourceService {

	/**
	 * Return list emp
	 * 
	 * @return list emp
	 */
	List<HumanResourceDto> getHumanResources();

	List<HumanResource> getHomeResourceList(int userId);
	HumanResourceDto getHumanResourceById(int id) throws NotFoundException;
	HumanResource getHumanResourceByEmail(String email);
	List<HumanResource> getHumanResourceByManagerId(int managerId);
	List<HumanResource> getAppliableResourceById(int projectId,int userId);

    String addHumanResource(HumanResource humanResource);

	String updateHumanResource(HumanResource humanResource);

	HumanResource viewHumanResourceDetails(int id);
	String changeResourceStatus(HumanResource humanResource);
	List<Job> viewHumanresourceHistory(int id);
	String removedResource(int resourceId);
}
