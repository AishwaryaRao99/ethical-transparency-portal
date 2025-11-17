package com.aishwarya.ethical.transparency_portal.exception_handling;

public class UnAuthorizedException extends ApiException {

    private static final long serialVersionUID = 1L; // <-- FIX FOR WARNING
    
	public UnAuthorizedException() {
		super(ErrorCode.UNAUTHORIZED);
	}

	public UnAuthorizedException(String message) {
		super(ErrorCode.UNAUTHORIZED, message);
	}
}
