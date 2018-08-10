package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.domain.jacksonview.UserView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.entities.User;

import java.util.List;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrssc.domain.dto.ManagerDto;
import com.hrssc.domain.dto.ResponseStatus;
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
	public boolean updateUser(@RequestBody ManagerDto user) {
		return userService.updateUser(user);
	}

	@DeleteMapping(value = "/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUserById(@PathVariable("userId") final Long userId) {
		userService.deleteUserById(userId);
	}

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseStatus addManager(@RequestBody User user){
		return new ResponseStatus(userService.addManager(user));
	}
	
	/**
	 * Return list manager by chefId
	 * @param companyId
	 * @return
	 */
	@GetMapping(value = "/{companyId}")
	public List<ManagerDto> getManagersByCompanyId(@PathVariable("companyId") int companyId) {
		return userService.getManagersByCompanyId(companyId);
	}

	@JsonView(UserView.details.class)
	@GetMapping(value = "/details/{UserId}")
	public User viewManagerDetails(@PathVariable("UserId") int userId) throws NotFoundException {
			return userService.viewManagerDetails(userId);
	}

	@JsonView(HumanResourceView.overview.class)
	@GetMapping(value = "/resource-list/{userId}")
	public List<HumanResource> getManagerResources(@PathVariable(value = "userId") int userId){
		return userService.getManagerResource(userId);
	}

	@JsonView(ProjectView.ListView.class)
	@GetMapping(value = "/project-list/{userId}")
	public List<Project> getManagerProjects(@PathVariable(value = "userId") int userId){
		return userService.getManagerProject(userId);
	}

	@GetMapping(value = "/deactive-managger/{managerId}")
	public ResponseStatus removedManager(@PathVariable(value = "managerId") int managerId){
		ResponseStatus responseStatus = new ResponseStatus(userService.deactiveManager(managerId));
		return responseStatus;
	}
}

