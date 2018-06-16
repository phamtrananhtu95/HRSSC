package com.hrssc.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.EmployeeDto;
import com.hrssc.service.EmployeesService;

@Service
public class EmployeesServiceImpl implements EmployeesService {

	@Override
	public List<EmployeeDto> getEmployees() {
		List<EmployeeDto> employeeList = new ArrayList<>();

		EmployeeDto emp1 = EmployeeDto.builder()
				.id(1L)
				.name("mapppp")
				.skill("java, c#")
				.email("tuhihi@gmail.com")
				.experience("2")
				.phone("0123456789")
				.company("abc software")
				.matched(5)
				.build();
		employeeList.add(emp1);

		EmployeeDto emp2 = EmployeeDto.builder()
				.id(1L)
				.name("heo")
				.skill("java, sql")
				.email("duonghihi@gmail.com")
				.experience("2")
				.phone("0123456789")
				.company("abc software")
				.matched(5)
				.build();
		employeeList.add(emp2);

		return employeeList;
	}
}
