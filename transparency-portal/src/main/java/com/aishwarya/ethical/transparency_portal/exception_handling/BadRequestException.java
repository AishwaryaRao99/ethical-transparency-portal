package com.aishwarya.ethical.transparency_portal.exception_handling;

public class BadRequestException extends ApiException {

    private static final long serialVersionUID = 1L; // <-- FIX FOR WARNING
    
	public BadRequestException() {
		super(ErrorCode.INVALID_USER_INPUT);
	}

	public BadRequestException(String message) {
		super(ErrorCode.INVALID_USER_INPUT, message);
	}
}
