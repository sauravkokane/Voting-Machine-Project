package com.votingmachine.validators.username;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsenameValidator implements ConstraintValidator<ValidUsername, String> {
	private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z0-9_]+$");
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
//		int letterCount = 0;
//        int digitUnderscoreCount = 0;
		if(username == null)
			return false;

//        for (char c : username.toCharArray()) {
//            if (Character.isLowerCase(c)) {
//                letterCount++;
//            } else if (Character.isDigit(c) || c == '_') {
//                digitUnderscoreCount++;
//            }
//        }
		return USERNAME_PATTERN.matcher(username).matches();
	}

}
