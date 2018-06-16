package com.hrssc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

	private Long id;

	private String name;

	private String dob;

	private String skill;

	private String experience;

	private String phone;

	private String email;
	
	private String company;
	
	private Integer matched;
}
