package com.hrssc.service.impl;

import org.springframework.stereotype.Service;

import com.hrssc.model.UserDto;
import com.hrssc.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

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
