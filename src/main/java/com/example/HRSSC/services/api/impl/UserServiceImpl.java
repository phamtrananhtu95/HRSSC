package com.example.HRSSC.services.api.impl;

import com.example.HRSSC.models.User;
import com.example.HRSSC.repository.UserRepository;
import com.example.HRSSC.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by Thien on 6/15/2018.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
