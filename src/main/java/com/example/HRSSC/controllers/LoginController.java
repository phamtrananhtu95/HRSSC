package com.example.HRSSC.controllers;

import com.example.HRSSC.models.User;
import com.example.HRSSC.services.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Thien on 6/15/2018.
 */

@RestController
@RequestMapping("")
public class LoginController {
    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    private String login(){
//        User user = userService.getAuthenticatedUser();
//        if (user == null){
//            return "false";
//        }else {
//            return "login page";
//        }
//
//    }

    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        logger.info("user logged "+principal);
        return principal;
    }
}
