package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.UserView;
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

import com.hrssc.domain.dto.UserDto;
import com.hrssc.service.ManagerManagementService;

@RestController
@RequestMapping("/manage-manager")
public class ManagerManagementController {

	@Autowired
	private ManagerManagementService userService;

	//@PostMapping(value = "/get/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	//public User getManagerById(@PathVariable("userId") int userId) {
	//	return userService.getManagerById(userId);
	//}

	@JsonView(UserView.overview.class)
	@PostMapping(value = "/get/{userId}")
	public User getManagerById(@PathVariable("userId") int userId) {
		return userService.getManagerById(userId);
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
        if(userService.addManager(user)){
            return "OK";
        }
        return "Email Existed.";
	}
}
