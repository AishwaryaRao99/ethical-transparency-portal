package com.aishwarya.ethical.transparency_portal.exception_handling;

public class UserNotFoundException extends ApiException {

    private static final long serialVersionUID = 1L; // <-- FIX FOR WARNING
    
	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}

	public UserNotFoundException(String message) {
		super(ErrorCode.USER_NOT_FOUND, message);
	}
}