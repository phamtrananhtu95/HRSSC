package com.hrssc.service;

import java.util.List;

import com.hrssc.domain.dto.ManagerDto;
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
	boolean updateUser(ManagerDto user);

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
	boolean addManager(ManagerDto user);

	List<ManagerDto> getManagersByCompanyId(int companyId);
}
