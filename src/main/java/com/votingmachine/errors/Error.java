package com.votingmachine.errors;

public class Error {
	private final String error;
	private final String errorMessage;
	
	public Error(String error, String errorMessage) {
		this.error = error;
		this.errorMessage = errorMessage;
	}
	public String getError(String error) {
		return error;
	}
	public String getErrorMessage(String errorMessage) {
		return errorMessage;
	}
	
}
