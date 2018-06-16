package com.hrssc.rest;

import com.hrssc.entities.User;
import com.hrssc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.hrssc.domain.dto.UserDto;
import com.hrssc.service.LoginService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
	@Autowired
	private UsersService userService;
	@Autowired
	private LoginService loginService;

//	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Boolean login(@RequestBody final UserDto user) {
//		return loginService.login(user);
//	}

	@GetMapping(value = {"/","/login"}, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	private User login(){
		return userService.getAuthenticatedUser();
	}

}
