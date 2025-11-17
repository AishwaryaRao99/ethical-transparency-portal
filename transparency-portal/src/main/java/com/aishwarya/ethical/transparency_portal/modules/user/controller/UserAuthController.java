package com.aishwarya.ethical.transparency_portal.modules.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aishwarya.ethical.transparency_portal.exception_handling.UserNotFoundException;
import com.aishwarya.ethical.transparency_portal.modules.user.service.UserService;
@RestController
@RequestMapping("/api/v1/users")
public class UserAuthController {
	private final UserService userService;
	public UserAuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/{id}")
	public String getUser(@PathVariable Long id) {
		if (id == 0) {
			throw new UserNotFoundException("User with ID 0 does not exist");
		}
		return userService.getUser(id);
	}

}
