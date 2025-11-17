package com.aishwarya.ethical.transparency_portal.exception_handling;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	// ---------- 1. Handles all custom ApiException types ----------
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, HttpServletRequest request) {
		log.error("API Exception: {} - {}", ex.getErrorCode(), ex.getMessage());
		ErrorCode code = ex.getErrorCode();

		ErrorResponse body = new ErrorResponse(code.name(), ex.getMessage(), code.getStatus().value(),
				request.getRequestURI(), LocalDateTime.now(), null);

		return ResponseEntity.status(code.getStatus()).body(body);
	}

	// ---------- 2. Validation errors ----------
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		log.error("MethodArgumentNotValidException : {}", ex.getMessage());
		List<FieldErrorDetail> details = ValidationUtils.fromBindingErrors(ex);
		ErrorCode code = ErrorCode.INVALID_USER_INPUT; // or a dedicated VALIDATION error code

		ErrorResponse body = new ErrorResponse(code.name(),
				details.isEmpty() ? code.getMessage() : "Validation failed", // general message
				code.getStatus().value(), request.getRequestURI(), LocalDateTime.now(), details);

		return ResponseEntity.status(code.getStatus()).body(body);
	}

	// ---------- 3. OPTIONAL explicit handlers for specific exceptions ----------

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
		log.error("UserNotFoundException: {} - {}", ex.getErrorCode(), ex.getMessage());
		return buildFromApiException(ex, request);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
		log.error("BadRequestException: {} - {}", ex.getErrorCode(), ex.getMessage());
		return buildFromApiException(ex, request);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex, HttpServletRequest request) {
		log.error("ConflictException : {} - {}", ex.getErrorCode(), ex.getMessage());
		return buildFromApiException(ex, request);
	}

	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorized(UnAuthorizedException ex, HttpServletRequest request) {
		log.error("UnAuthorizedException : {} - {}", ex.getErrorCode(), ex.getMessage());
		return buildFromApiException(ex, request);
	}

	private ResponseEntity<ErrorResponse> buildFromApiException(ApiException ex, HttpServletRequest request) {
		ErrorCode code = ex.getErrorCode();

		ErrorResponse body = new ErrorResponse(code.name(), ex.getMessage(), code.getStatus().value(),
				request.getRequestURI(), LocalDateTime.now(), null);
		return ResponseEntity.status(code.getStatus()).body(body);
	}

	// ---------- 4. Fallback for unknown exceptions ----------
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
		
		log.error("Unhandled exception {} ", ex.getMessage());

		ErrorCode code = ErrorCode.INTERNAL_ERROR;

		ErrorResponse body = new ErrorResponse(code.name(), code.getMessage(), code.getStatus().value(),
				request.getRequestURI(), LocalDateTime.now(), null);

		return ResponseEntity.status(code.getStatus()).body(body);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException ex) {

		String traceId = MDC.get("traceId");

	    log.error("Handled ApiException: type={}, message={}, traceId={}",
	            ex.getErrorCode(),
	            ex.getMessage(),
	            traceId
	    );

	    return new ResponseEntity<>(
	            ApiExceptionResponse.from(ex, traceId),
	            ex.getErrorCode().getStatus()
	    );
	}
}
