package com.votingmachine.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoders {
	private Encoders() {
		System.err.println("Encoder object cannot be created");
	}
	public static BCryptPasswordEncoder getBCryptEncoder() {
		return new BCryptPasswordEncoder();
	}
}
