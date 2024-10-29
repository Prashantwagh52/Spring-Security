package com.prashant.SpringSecurity.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.prashant.SpringSecurity.dto.User;

@Service
public class UserService {

	private List<User> users = new ArrayList<>();
	public UserService() {
		this.users.add(new User(UUID.randomUUID(), "user1", "12"));
		this.users.add(new User(UUID.randomUUID(), "user2", "12"));
		this.users.add(new User(UUID.randomUUID(), "user3", "12"));
		this.users.add(new User(UUID.randomUUID(), "user4", "12"));
		this.users.add(new User(UUID.randomUUID(), "user5", "12"));
	}
	public List<User> getUsers() {
		return this.users;
	}
	
	public String getCurrentLoginUserName(Principal principal) {
		return principal.getName();
	}
}
