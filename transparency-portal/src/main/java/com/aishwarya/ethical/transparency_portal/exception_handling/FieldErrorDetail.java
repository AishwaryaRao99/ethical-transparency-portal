package com.aishwarya.ethical.transparency_portal.exception_handling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorDetail {
	private String field;
	private String message;
}
