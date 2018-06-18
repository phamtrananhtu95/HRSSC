package com.hrssc.service.impl;

import com.hrssc.entities.User;
import com.hrssc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

import com.hrssc.domain.dto.UserDto;
import com.hrssc.service.ManagerManagementService;

@Service
public class ManagerManagementServiceImpl implements ManagerManagementService {

	private static Logger logger = Logger.getLogger(ManagerManagementServiceImpl.class.getName());

	@Autowired
	private UserRepository userRepository;

	public UserDto getUserById(final Long userId) {
		return UserDto.builder().username("admin").password("admin").role("admin").build();
	}

	@Override
	public boolean updateUser(final User userInfo) {
		final Optional<User> userEntity = userRepository.findByUsername(userInfo.getUsername());

		if (!userEntity.isPresent()) {
			final User updatedUser = userEntity.get();
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
	public boolean addUser(User user) {
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
	public User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Optional<User> user = userRepository.findByUsername(username);
		return user.isPresent() ? user.get() : new User();
	}
}
