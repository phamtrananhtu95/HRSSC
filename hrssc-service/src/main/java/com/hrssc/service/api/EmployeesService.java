package com.hrssc.service.api;

import java.util.List;

import com.hrssc.domain.dto.EmployeeDto;

public interface EmployeesService {

	/**
	 * Return list emp
	 * 
	 * @return list emp
	 */
	List<EmployeeDto> getEmployees();
}
