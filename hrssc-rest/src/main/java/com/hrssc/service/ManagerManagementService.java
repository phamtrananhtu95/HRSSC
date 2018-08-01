package com.hrssc.service;

import java.util.List;

import com.hrssc.domain.dto.ManagerDto;
import com.hrssc.domain.dto.UserDto;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.entities.User;
import javassist.NotFoundException;

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
	String addManager(User user);

	User viewManagerDetails(int id) throws NotFoundException;

	List<ManagerDto> getManagersByCompanyId(int companyId);
	List<HumanResource> getManagerResource(int userId);
	List<Project> getManagerProject(int userId);
}
