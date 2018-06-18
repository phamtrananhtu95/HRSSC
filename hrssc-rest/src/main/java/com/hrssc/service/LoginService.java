package com.hrssc.service;

import com.hrssc.domain.dto.UserDto;

public interface LoginService {

	Boolean login(final UserDto user);
	
}
