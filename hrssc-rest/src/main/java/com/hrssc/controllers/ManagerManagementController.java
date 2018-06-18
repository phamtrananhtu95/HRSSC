package com.hrssc.controllers;

import com.hrssc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrssc.model.UserDto;
import com.hrssc.service.ManagerManagementService;

@RestController
@RequestMapping("/manage-manager")
public class ManagerManagementController {

	@Autowired
	private ManagerManagementService userService;

	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto getUserById(@PathVariable("userId") final Long userId) {
		return userService.getUserById(userId);
	}

	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping(value = "/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUserById(@PathVariable("userId") final Long userId) {
		userService.deleteUserById(userId);
	}

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addManager(@RequestBody User user){
        if(userService.addUser(user)){
            return "OK";
        }
        return "Email Existed";
	}
}
