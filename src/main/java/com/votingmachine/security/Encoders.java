package com.votingmachine.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoders {
	private Encoders() {
		System.err.println("Encoder object cannot be created");
	}
	@Bean
	public static BCryptPasswordEncoder getBCryptEncoder() {
		return new BCryptPasswordEncoder();
	}
}
