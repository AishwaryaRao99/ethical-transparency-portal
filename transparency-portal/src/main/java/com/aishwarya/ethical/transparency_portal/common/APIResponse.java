package com.aishwarya.ethical.transparency_portal.common;

public class APIResponse {
// A simple wrapper for API responses. You can expand this class to include additional fields like status, error messages, etc.
	private String message;

	public APIResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
