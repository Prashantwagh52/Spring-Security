package com.prashant.SpringSecurity.dto;

import java.util.UUID;

public class User {

	private UUID userId;
	private String name;
	private String age;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public User(UUID userId, String name, String age) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
	}

	public User() {
		super();
	}

}
