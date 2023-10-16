package com.votingmachine.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component("Bcrypt")
public class PasswordTranslator {
	private static BCryptPasswordEncoder encoder = Encoders.getBCryptEncoder();
	
	public String encryptPassword(String password) {
		String hashedPassword = encoder.encode(password);
		return hashedPassword;
	}
	
	public boolean decryptPassword(String enteredPassword, String storedHashedPassword) {
		if(encoder.matches(enteredPassword, storedHashedPassword))
			return true;
		else
			return false;
	}
}
