package com.hrssc.service;

import com.hrssc.domain.dto.UserDto;
import com.hrssc.entities.User;

public interface ManagerManagementService {

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
	boolean updateUser(User user);

	/**
	 * Deletes user by user id
	 * 
	 * @param userId
	 * @return user by user id
	 */
	void deleteUserById(Long userId);
	
	void login(boolean checkLogin);

	User getAuthenticatedUser();

	boolean checkExistingEmail(String email);
	boolean addUser(User user);
}
