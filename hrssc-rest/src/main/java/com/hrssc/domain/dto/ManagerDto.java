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
	private Integer id;
	private String fullname;
	private String password;
	private String username;
	private String email;
	private String tel;
	private Integer status;
	private Integer projectNum;
	private Integer resourceNum;
	private Integer companyId;
	
	public ManagerDto(User user) {
		this.id = user.getId();
		this.fullname = user.getFullname();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.tel = user.getTel();
		this.status = user.getStatus();
		this.projectNum = user.getProjectsById().size();
		this.resourceNum = user.getHumanResourcesById().size();
	}
}
