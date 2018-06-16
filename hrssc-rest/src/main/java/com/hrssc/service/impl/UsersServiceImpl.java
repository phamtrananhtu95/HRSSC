package com.hrssc.service.impl;

import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.UserDto;
import com.hrssc.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	public UserDto getUserById(final Long userId) {
		return UserDto.builder()
		.username("admin")
		.password("admin")
		.role("admin")
		.build();
	}

	@Override
	public UserDto updateUser(final UserDto user) {
		//TODO: Save object user to DB
		// Ex: userRepo.save(user)
		return user;
	}

	@Override
	public void deleteUserById(final Long userId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void login(boolean checkLogin) {
		// TODO Auto-generated method stub
		
	}

}
