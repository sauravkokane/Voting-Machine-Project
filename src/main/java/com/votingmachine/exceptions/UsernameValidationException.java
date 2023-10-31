package com.votingmachine.exceptions;

public class UsernameValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameValidationException(String message) {
		super(message);
	}
}
