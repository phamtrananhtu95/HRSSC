package com.hrssc.service.api;

import com.hrssc.domain.dto.UserDto;

public interface UsersService {

	/**
	 * Returns user by user id
	 * 
	 * @param userId
	 * @return user by user id
	 */
	UserDto getUserById(final Long userId);

	/**
	 * Updates user
	 * 
	 * @param user
	 * @return updated user
	 */
	UserDto update(final UserDto user);

	/**
	 * Delete user by user id
	 * 
	 * @param userId
	 * @return user by user id
	 */
	void delete(final Long userId);
}
