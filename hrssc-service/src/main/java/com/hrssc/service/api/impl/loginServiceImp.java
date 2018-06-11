package com.hrssc.service.api.impl;

import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.UserDto;
import com.hrssc.service.api.loginService;

@Service
public class loginServiceImp implements loginService{

	@Override
	public Boolean login(UserDto user) {
		
		UserDto userObj = UserDto.builder()
				.username(user.getUsername())
				.password("admin")
				.role("admin")
				.build();
		return user.getPassword().equals(userObj.getPassword());
	}
}
