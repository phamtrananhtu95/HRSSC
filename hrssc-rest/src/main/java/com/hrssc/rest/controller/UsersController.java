package com.hrssc.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrssc.domain.dto.UserDto;
import com.hrssc.service.api.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService userService;

	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto getUserById(@PathVariable("userId") final Long userId) {
		return userService.getUserById(userId);
	}

	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto updateUser(@RequestBody final UserDto user) {
		return userService.updateUser(user);
	}
}
