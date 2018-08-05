package com.hrssc.service.impl;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.entities.User;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.hrssc.domain.Constant;
import com.hrssc.domain.dto.ManagerDto;
import com.hrssc.domain.dto.UserDto;
import com.hrssc.service.ManagerManagementService;

@Service
public class ManagerManagementServiceImpl implements ManagerManagementService {

	private static Logger logger = Logger.getLogger(ManagerManagementServiceImpl.class.getName());

	@Autowired
	private UserRepository userRepository;


	@Autowired
    private ProjectRepository projectRepository;

	@Autowired
    private HumanResourceRepository humanResourceRepository;

	public User getManagerById(int id) {
        User user = userRepository.findByIdAndRoleId(id,Constant.UserRole.MANAGER);
	    return user;
	}

	@Override
	public boolean updateUser(final ManagerDto userInfo) {
		final Optional<User> userEntity = userRepository.findByUsername(userInfo.getUsername());

		if (userEntity.isPresent()) {
			final com.hrssc.entities.User updatedUser = userEntity.get();
			updatedUser.setEmail(userInfo.getEmail());
			updatedUser.setFullname(userInfo.getFullname());
			updatedUser.setTel(userInfo.getTel());
			updatedUser.setStatus(userInfo.getStatus());
			userRepository.save(updatedUser);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteUserById(final Long userId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void login(boolean checkLogin) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkExistingEmail(String email) {

		if (userRepository.findByEmail(email) != null) {
			return true;
		}
		return false;

	}

	@Override
	public String addManager(User user){
		user.setUsername(user.getEmail());
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (!this.checkExistingEmail(user.getEmail())) {

			user.setStatus(Constant.ManagerStatus.ACTIVATED);
			user.setFirstLogin(true);
			user.setRoleId(Constant.UserRole.MANAGER);
			userRepository.save(user);
			return "OK";
		}
		return "Email Existed.";
	}

	@Override
	public com.hrssc.entities.User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Optional<com.hrssc.entities.User> user = userRepository.findByUsername(username);
		return user.isPresent() ? user.get() : new com.hrssc.entities.User();
	}

	@Override
	public List<ManagerDto> getManagersByCompanyId(int companyId) {
		List<User> users = userRepository.getByCompanyIdAndRoleId(companyId, Constant.MANAGER_ROLE_ID);
		List<ManagerDto> managers = new ArrayList<>();
		if(users.isEmpty()) {
			return Collections.emptyList();
		}
		
		users.stream().forEach(user -> {
			managers.add(new ManagerDto(user));
		});
		
		return managers;
	}

	@Override
	public User viewManagerDetails(int id) throws NotFoundException {
			User userDetails =  userRepository.findByIdAndRoleId(id,Constant.UserRole.MANAGER);;
			if(userDetails == null){
				throw new NotFoundException("not found Manager with id ...");

			}
			return userDetails;

	}

	public List<HumanResource> getManagerResource(int userId){
        return humanResourceRepository.findByUserIdAndStatus(userId,Constant.ResourceStatus.AVAILABLE);
    }
    public List<Project> getManagerProject(int userId){
        return projectRepository.findByUserIdAndRequestStatus(userId,Constant.RequestStatus.OPENNING);
    }

}
