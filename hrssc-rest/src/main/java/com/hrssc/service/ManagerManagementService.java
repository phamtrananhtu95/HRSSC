package com.hrssc.service;

import com.hrssc.domain.dto.UserDto;
import com.hrssc.entities.User;

public interface ManagerManagementService {

	/**
	 * Returns user by user id
	 * 
	 * @param id
	 * @return user by user id
	 */
	User getManagerById(int id);

	/**
	 * Updates user
	 * 
	 * @param user
	 * @return updated user
	 */
	boolean updateUser(com.hrssc.entities.User user);

	/**
	 * Deletes user by user id
	 * 
	 * @param userId
	 * @return user by user id
	 */
	void deleteUserById(Long userId);
	
	void login(boolean checkLogin);

	com.hrssc.entities.User getAuthenticatedUser();

	boolean checkExistingEmail(String email);
	boolean addManager(com.hrssc.entities.User user);
}
