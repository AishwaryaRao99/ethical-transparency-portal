package com.aishwarya.ethical.transparency_portal.exception_handling;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiExceptionResponse {

    private String errorCode;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;

    private String traceId;

    public static ApiExceptionResponse from(ApiException ex, String traceId) {
        return ApiExceptionResponse.builder()
                .errorCode(ex.getErrorCode().getCode())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .traceId(traceId)
                .build();
    }
}
