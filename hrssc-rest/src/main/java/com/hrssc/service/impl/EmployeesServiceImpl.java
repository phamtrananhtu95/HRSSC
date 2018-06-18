package com.hrssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrssc.model.EmployeeDto;
import com.hrssc.entities.HumanResource;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.service.EmployeesService;

@Service
public class EmployeesServiceImpl implements EmployeesService {
	@Autowired
	private HumanResourceRepository humanResourceRepository;

	@Override
	public List<EmployeeDto> getEmployees() {
		List<EmployeeDto> employeeList = new ArrayList<>();
		List<HumanResource> humanResources = humanResourceRepository.findAll();
		humanResources.stream().forEach(item -> {
			employeeList.add(new EmployeeDto().getEmployee(item));
		});

		return employeeList;
	}
}
