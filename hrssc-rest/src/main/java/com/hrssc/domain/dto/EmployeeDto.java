package com.hrssc.domain.dto;

import com.hrssc.entities.HumanResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	private int id;
	private String fullname;
	private int status;
	private String email;
	private String tel;
	private Long availableDate;
	private Long availableDuration;

	public EmployeeDto getEmployee(final HumanResource humanResource) {
		return EmployeeDto.builder().id(humanResource.getId()).fullname(humanResource.getFullname())
				.status(humanResource.getStatus()).email(humanResource.getEmail()).tel(humanResource.getTel())
				.availableDate(humanResource.getAvailableDate()).availableDuration(humanResource.getAvailableDuration())
				.build();
	}
}
