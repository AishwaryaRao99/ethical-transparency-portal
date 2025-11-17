package com.aishwarya.ethical.transparency_portal.exception_handling;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;

public final class ValidationUtils {
	private ValidationUtils() {
	}

	public static List<FieldErrorDetail> fromBindingErrors(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(f -> new FieldErrorDetail(f.getField(), f.getDefaultMessage())).collect(Collectors.toList());
	}
}