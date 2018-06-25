package com.hrssc.service.impl;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.entities.User;
import com.hrssc.repository.UserRepository;
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

	public User getManagerById(int id) {
        User user = userRepository.findManagerById(id);
	    return user;
	}

	@Override
	public boolean updateUser(final com.hrssc.entities.User userInfo) {
		final Optional<com.hrssc.entities.User> userEntity = userRepository.findByUsername(userInfo.getUsername());

		if (!userEntity.isPresent()) {
			final com.hrssc.entities.User updatedUser = userEntity.get();
			updatedUser.setId(userInfo.getId());
			updatedUser.setEmail(userInfo.getEmail());
			updatedUser.setFirstLogin(userInfo.isFirstLogin());
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
	public boolean addManager(com.hrssc.entities.User user) {
		user.setUsername(user.getEmail());
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (!this.checkExistingEmail(user.getEmail())) {
			userRepository.save(user);
			return true;
		}
		return false;
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

}
