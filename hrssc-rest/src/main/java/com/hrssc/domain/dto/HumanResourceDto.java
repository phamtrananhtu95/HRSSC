package com.hrssc.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hrssc.entities.HumanResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HumanResourceDto {
	private int id;
	private String fullname;
	private int status;
	private String email;
	private String tel;
	private Long availableDate;
	private Long availableDuration;
	private List<String> skills;
	private String company;

	public HumanResourceDto getHumanResource(HumanResource humanResource) {
		List<String> skills = humanResource.getResourceSkillsById().stream()
				.map(resourceSkill -> resourceSkill.getSkillBySkillId().getTitle()).collect(Collectors.toList());

		String company = humanResource.getCompanyByCompanyId().getName();
		
		return HumanResourceDto.builder().id(humanResource.getId()).fullname(humanResource.getFullname())
				.status(humanResource.getStatus()).email(humanResource.getEmail()).tel(humanResource.getTel())
				.availableDate(humanResource.getAvailableDate()).availableDuration(humanResource.getAvailableDuration())
				.skills(skills).company(company).build();
	}
}
