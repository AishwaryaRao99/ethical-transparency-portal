package com.aishwarya.ethical.transparency_portal.exception_handling;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private String errorCode; // e.g. USR_001
	private String message; // human-friendly message
	private int status; // HTTP status code
	private String path; // request URI
	private LocalDateTime timestamp; // time
	private List<FieldErrorDetail> fieldErrors; // optional, for validations
}
