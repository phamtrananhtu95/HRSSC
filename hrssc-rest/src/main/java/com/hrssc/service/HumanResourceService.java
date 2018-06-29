package com.hrssc.service;

import java.util.List;

import com.hrssc.domain.dto.HumanResourceDto;

import com.hrssc.domain.dto.HumanResourceSkillDTO;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Skill;
import javassist.NotFoundException;



public interface HumanResourceService {

	/**
	 * Return list emp
	 * 
	 * @return list emp
	 */
	List<HumanResourceDto> getHumanResources();

	HumanResourceDto getHumanResourceById(int id) throws NotFoundException;

	List<HumanResource> getHumanResourceByManagerId(int managerId);

    String addHumanResource(HumanResource humanResource);

	String updateHumanResource(HumanResource humanResource);

	HumanResource viewHumanResourceDetails(int id);
	String changeResourceStatus(HumanResource humanResource);
}
