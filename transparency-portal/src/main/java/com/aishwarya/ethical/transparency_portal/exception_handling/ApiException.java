package com.aishwarya.ethical.transparency_portal.exception_handling;

import lombok.Getter;

@Getter
public abstract class ApiException extends RuntimeException {
	private final ErrorCode errorCode;

    private static final long serialVersionUID = 1L; // <-- FIX FOR WARNING
    
	protected ApiException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	protected ApiException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}