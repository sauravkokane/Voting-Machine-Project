package com.votingmachine.MyResponseProvider;

import java.util.HashMap;

public class ResponseProvider {

	private static ResponseProvider resProvider;
	private HashMap<String, ?> response;

	private ResponseProvider() {
		this.response = new HashMap<>();
	}

	public static ResponseProvider getResponseProvider() {
		if (resProvider == null) {
			synchronized (ResponseProvider.class) {
				// Double-check locking to ensure thread safety
				if (resProvider == null) {
					resProvider = new ResponseProvider();
				}
			}

		}
		return resProvider;
	}

}
