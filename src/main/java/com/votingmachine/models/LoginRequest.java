package com.votingmachine.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private final String loginIdentifier;
	@NotBlank
	private final String  password;
	
	public LoginRequest(@JsonProperty("Email id or Usename") String loginIdentifier, @JsonProperty("password") String password) {
		this.loginIdentifier = loginIdentifier;
		this.password = password;
	}
	
	public String getLoginIdentifier() {
		return loginIdentifier;
	}
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "LoginRequest [loginIdentifier=" + loginIdentifier + ", password=" + password + "]";
	}
	
}
