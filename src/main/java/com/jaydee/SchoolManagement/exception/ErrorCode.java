package com.jaydee.SchoolManagement.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Validation Errors
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Validation failed"),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "INVALID_INPUT", "Invalid input provided"),
    DUPLICATE_ENTRY(HttpStatus.CONFLICT, "DUPLICATE_ENTRY", "Resource already exists"),
    
    // Authentication & Authorization
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "Authentication required"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "Access denied"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "Invalid username or password"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED", "Authentication token has expired"),
    
    // Resource Errors - Generic, not specific to Student
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND", "Requested resource not found"),
    RESOURCE_ALREADY_EXISTS(HttpStatus.CONFLICT, "RESOURCE_ALREADY_EXISTS", "Resource already exists"),
    RESOURCE_LOCKED(HttpStatus.LOCKED, "RESOURCE_LOCKED", "Resource is currently locked"),
    
    // Business Logic Errors
    ATTENDANCE_ALREADY_MARKED(HttpStatus.CONFLICT, "ATTENDANCE_ALREADY_MARKED", "Attendance already marked for this student, class, and date"),
    CLASS_NOT_ACTIVE(HttpStatus.BAD_REQUEST, "CLASS_NOT_ACTIVE", "Class is not currently active"),
    ENROLLMENT_REQUIRED(HttpStatus.BAD_REQUEST, "ENROLLMENT_REQUIRED", "Student must be enrolled in the class"),
    
    // Server Errors
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "An unexpected server error occurred"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "SERVICE_UNAVAILABLE", "Service is temporarily unavailable"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DATABASE_ERROR", "Database operation failed");

    private final HttpStatus httpStatus;
    private final String code;
    private final String defaultMessage;

}

