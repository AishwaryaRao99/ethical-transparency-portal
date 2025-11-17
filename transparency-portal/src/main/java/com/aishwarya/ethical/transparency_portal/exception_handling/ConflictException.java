package com.aishwarya.ethical.transparency_portal.exception_handling;

public class ConflictException extends ApiException {

    private static final long serialVersionUID = 1L; // <-- FIX FOR WARNING
    
	public ConflictException(ErrorCode code) {
		super(code);
	}

	public ConflictException(ErrorCode code, String message) {
		super(code, message);
	}
}