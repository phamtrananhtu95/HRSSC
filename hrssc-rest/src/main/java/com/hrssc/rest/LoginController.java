package com.hrssc.rest;

import com.hrssc.entities.User;
import com.hrssc.service.ManagerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hrssc.service.LoginService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
	@Autowired
	private ManagerManagementService userService;
	@Autowired
	private LoginService loginService;

//	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Boolean login(@RequestBody final UserDto user) {
//		return loginService.login(user);
//	}


	@GetMapping(value = {"/","/login"})
	@CrossOrigin
	private String login(){
		User user = userService.getAuthenticatedUser();
		if(user!=null){
			return user.getUsername();
		}
		return null;
	}

}
