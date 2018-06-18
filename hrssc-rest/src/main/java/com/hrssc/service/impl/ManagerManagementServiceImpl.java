package com.hrssc.service.impl;

import com.hrssc.entities.User;
import com.hrssc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import com.hrssc.model.UserDto;
import com.hrssc.service.ManagerManagementService;

@Service
public class ManagerManagementServiceImpl implements ManagerManagementService {

    private static Logger logger = Logger.getLogger(ManagerManagementServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    public UserDto getUserById(final Long userId) {
        return UserDto.builder()
                .username("admin")
                .password("admin")
                .role("admin")
                .build();
    }

    @Override
    public boolean updateUser(User user) {
        User tmp = userRepository.findByUsername(user.getUsername());
        if (tmp != null) {
            tmp.setId(user.getId());
            tmp.setEmail(user.getEmail());
            tmp.setFirstLogin(user.isFirstLogin());
            tmp.setFullname(user.getFullname());
            tmp.setTel(user.getTel());
            tmp.setStatus(user.getStatus());

            userRepository.save(tmp);
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
        return userRepository.findByUsername(username);
    }

}
