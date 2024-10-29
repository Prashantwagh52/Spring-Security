package com.prashant.SpringSecurity.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.SpringSecurity.service.UserService;

@RestController
@RequestMapping("/api")
public class DemoController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/users")
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}

	@GetMapping("/current-user")
	public ResponseEntity<?> getCurrentLoginUserName(Principal principal) {
		return ResponseEntity.ok(userService.getCurrentLoginUserName(principal));
	}

	
	
}
