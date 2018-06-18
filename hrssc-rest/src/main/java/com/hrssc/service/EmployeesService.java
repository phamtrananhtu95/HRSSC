package com.hrssc.service;

import java.util.List;

import com.hrssc.model.EmployeeDto;



public interface EmployeesService {

	/**
	 * Return list emp
	 * 
	 * @return list emp
	 */
	List<EmployeeDto> getEmployees();
}
