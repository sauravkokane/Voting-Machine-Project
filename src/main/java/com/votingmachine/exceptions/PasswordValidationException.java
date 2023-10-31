package com.votingmachine.exceptions;

public class PasswordValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7843981379005809732L;

	public PasswordValidationException(String message) {
		super(message);
	}
}
