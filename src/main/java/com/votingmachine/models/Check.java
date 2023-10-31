package com.votingmachine.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Check {
	private final String token;
	public Check(@JsonProperty("token") String token){
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}