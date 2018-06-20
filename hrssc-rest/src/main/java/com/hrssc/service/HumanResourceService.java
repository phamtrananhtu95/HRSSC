package com.hrssc.service;

import java.util.List;

import com.hrssc.domain.dto.HumanResourceDto;

import com.hrssc.entities.HumanResource;
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
}
