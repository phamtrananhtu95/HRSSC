package com.hrssc.domain.dto;

import com.hrssc.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
	private String name;
	private String email;
	private String tel;
	private Integer status;
	private Integer projectNum;
	private Integer resourceNum;
	
	public ManagerDto(User user) {
		this.name = user.getFullname();
		this.email = user.getEmail();
		this.tel = user.getTel();
		this.status = user.getStatus();
		this.projectNum = user.getProjectsById().size();
		this.resourceNum = user.getHumanResourcesById().size();
	}
}
