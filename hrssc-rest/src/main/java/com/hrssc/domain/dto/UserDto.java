package com.hrssc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
	private String role;
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String tel;
	private Integer status;
	private boolean isFirstLogin;
	private int companyId;
}
