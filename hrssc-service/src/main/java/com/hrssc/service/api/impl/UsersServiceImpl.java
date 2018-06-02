package com.hrssc.service.api.impl;

import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.UserDto;
import com.hrssc.service.api.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	public UserDto getUserById(final Long userId) {
		return UserDto.builder()
		.id(userId)
		.userName("mapppp")
		.firstName("heo")
		.lastName("map")
		.phone("123456789")
		.email("quynhduong92@gmail.com")
		.address("020 Tran Tan")
		.build();
	}

	@Override
	public UserDto updateUser(final UserDto user) {
		//TODO: Save object user to DB
		// Ex: userRepo.save(user)
		return user;
	}

}
