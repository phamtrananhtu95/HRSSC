package com.hrssc.service.api;

import com.hrssc.domain.dto.UserDto;

public interface loginService {

	Boolean login(final UserDto user);
	
}
