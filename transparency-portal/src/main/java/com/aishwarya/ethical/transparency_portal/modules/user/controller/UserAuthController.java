package com.aishwarya.ethical.transparency_portal.modules.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserAuthController {
	
	@GetMapping("/{id}")
	public String getUsers(@PathVariable String id) {
		return "User ID: " + id;
	}

}
