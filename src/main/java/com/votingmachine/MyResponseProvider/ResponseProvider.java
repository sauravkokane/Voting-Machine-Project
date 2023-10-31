package com.votingmachine.MyResponseProvider;

import java.util.HashMap;
import java.util.Map;

public class ResponseProvider {
	public static Map<String, Object> responseBody;
	public static void configureResponse() {
		responseBody =  new HashMap<>();
	}
	public static Map<String, Object> getResponse() {
		return responseBody;
	}
}
