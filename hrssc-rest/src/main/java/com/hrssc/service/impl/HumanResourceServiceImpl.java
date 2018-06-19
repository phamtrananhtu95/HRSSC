package com.hrssc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
