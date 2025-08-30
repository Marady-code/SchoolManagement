package com.jaydee.SchoolManagement.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle custom ApiException
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, WebRequest request) {
        log.error("API Exception: ", ex);
        
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse.ErrorResponseBuilder errorBuilder = ErrorResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .error(errorCode.getHttpStatus().getReasonPhrase())
                .errorCode(errorCode.getCode())
                .message(ex.getMessage())
                .path(getPath(request))
                .timestamp(LocalDateTime.now());

        // Add validation details for ValidationException
        if (ex instanceof ValidationException) {
            ValidationException validationEx = (ValidationException) ex;
            if (validationEx.getValidationErrors() != null) {
                errorBuilder.details(validationEx.getValidationErrors());
            }
        }

        return new ResponseEntity<>(errorBuilder.build(), errorCode.getHttpStatus());
    }

    // Handle Spring validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Validation error: ", ex);
        
        Map<String, String> validationErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage(),
                        (existing, replacement) -> existing + "; " + replacement
                ));

        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.VALIDATION_ERROR.getHttpStatus().value())
                .error(ErrorCode.VALIDATION_ERROR.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.VALIDATION_ERROR.getCode())
                .message("Validation failed for one or more fields")
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .details(validationErrors)
                .build();

        return new ResponseEntity<>(error, ErrorCode.VALIDATION_ERROR.getHttpStatus());
    }

    // Handle constraint validation errors
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.error("Constraint violation: ", ex);
        
        Map<String, String> violations = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            violations.put(fieldName, violation.getMessage());
        });

        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.VALIDATION_ERROR.getHttpStatus().value())
                .error(ErrorCode.VALIDATION_ERROR.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.VALIDATION_ERROR.getCode())
                .message("Constraint validation failed")
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .details(violations)
                .build();

        return new ResponseEntity<>(error, ErrorCode.VALIDATION_ERROR.getHttpStatus());
    }

    // Handle EntityNotFound - now generic
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        log.error("Entity not found: ", ex);
        
        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.RESOURCE_NOT_FOUND.getHttpStatus().value())
                .error(ErrorCode.RESOURCE_NOT_FOUND.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND.getCode())
                .message(ex.getMessage() != null ? ex.getMessage() : "Requested resource not found")
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, ErrorCode.RESOURCE_NOT_FOUND.getHttpStatus());
    }

    // Handle data integrity violations (duplicate keys, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        log.error("Data integrity violation: ", ex);
        
        String message = "Data integrity violation occurred";
        if (ex.getMessage() != null && ex.getMessage().contains("Duplicate entry")) {
            message = "Resource with this information already exists";
        }

        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.DUPLICATE_ENTRY.getHttpStatus().value())
                .error(ErrorCode.DUPLICATE_ENTRY.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.DUPLICATE_ENTRY.getCode())
                .message(message)
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, ErrorCode.DUPLICATE_ENTRY.getHttpStatus());
    }

    // Handle method argument type mismatch
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.error("Type mismatch: ", ex);
        
        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s",
                ex.getValue(), ex.getName(), ex.getRequiredType().getSimpleName());

        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.INVALID_INPUT.getHttpStatus().value())
                .error(ErrorCode.INVALID_INPUT.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.INVALID_INPUT.getCode())
                .message(message)
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, ErrorCode.INVALID_INPUT.getHttpStatus());
    }

    // Handle missing request parameters
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex, WebRequest request) {
        log.error("Missing parameter: ", ex);
        
        String message = String.format("Required parameter '%s' is missing", ex.getParameterName());

        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.INVALID_INPUT.getHttpStatus().value())
                .error(ErrorCode.INVALID_INPUT.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.INVALID_INPUT.getCode())
                .message(message)
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, ErrorCode.INVALID_INPUT.getHttpStatus());
    }

    // Handle unsupported HTTP methods
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        log.error("Method not supported: ", ex);
        
        String message = String.format("HTTP method '%s' is not supported for this endpoint", ex.getMethod());

        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.INVALID_INPUT.getHttpStatus().value())
                .error(ErrorCode.INVALID_INPUT.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.INVALID_INPUT.getCode())
                .message(message)
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, ErrorCode.INVALID_INPUT.getHttpStatus());
    }

    // Handle malformed JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        log.error("Message not readable: ", ex);
        
        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.INVALID_INPUT.getHttpStatus().value())
                .error(ErrorCode.INVALID_INPUT.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.INVALID_INPUT.getCode())
                .message("Malformed JSON request")
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, ErrorCode.INVALID_INPUT.getHttpStatus());
    }

    // Handle all other unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
        log.error("Unexpected error: ", ex);
        
        ErrorResponse error = ErrorResponse.builder()
                .status(ErrorCode.INTERNAL_ERROR.getHttpStatus().value())
                .error(ErrorCode.INTERNAL_ERROR.getHttpStatus().getReasonPhrase())
                .errorCode(ErrorCode.INTERNAL_ERROR.getCode())
                .message("An unexpected error occurred")
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, ErrorCode.INTERNAL_ERROR.getHttpStatus());
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}

//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    // Handle custom ApiException
//    @ExceptionHandler(ApiException.class)
//    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, WebRequest request) {
//        ErrorCode errorCode = ex.getErrorCode();
//        ErrorResponse error = new ErrorResponse(
//                errorCode.getHttpStatus().value(),
//                errorCode.getHttpStatus().getReasonPhrase(),
//                errorCode.getCode(),
//                ex.getMessage(),
//                request.getDescription(false).replace("uri=", ""),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(error, errorCode.getHttpStatus());
//    }
//
//    // Handle validation errors
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
//        String validationMessage = ex.getBindingResult().getFieldErrors()
//                .stream().map(err -> err.getField() + ": " + err.getDefaultMessage())
//                .findFirst().orElse(ex.getMessage());
//
//        ErrorResponse error = new ErrorResponse(
//                ErrorCode.VALIDATION_ERROR.getHttpStatus().value(),
//                ErrorCode.VALIDATION_ERROR.getHttpStatus().getReasonPhrase(),
//                ErrorCode.VALIDATION_ERROR.getCode(),
//                validationMessage,
//                request.getDescription(false).replace("uri=", ""),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(error, ErrorCode.VALIDATION_ERROR.getHttpStatus());
//    }
//
//    // Handle EntityNotFound
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
//        ErrorResponse error = new ErrorResponse(
//                ErrorCode.STUDENT_NOT_FOUND.getHttpStatus().value(),
//                ErrorCode.STUDENT_NOT_FOUND.getHttpStatus().getReasonPhrase(),
//                ErrorCode.STUDENT_NOT_FOUND.getCode(),
//                ex.getMessage(),
//                request.getDescription(false).replace("uri=", ""),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(error, ErrorCode.STUDENT_NOT_FOUND.getHttpStatus());
//    }
//
//    // Handle unexpected errors
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
//        ErrorResponse error = new ErrorResponse(
//                ErrorCode.INTERNAL_ERROR.getHttpStatus().value(),
//                ErrorCode.INTERNAL_ERROR.getHttpStatus().getReasonPhrase(),
//                ErrorCode.INTERNAL_ERROR.getCode(),
//                ex.getMessage(),
//                request.getDescription(false).replace("uri=", ""),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(error, ErrorCode.INTERNAL_ERROR.getHttpStatus());
//    }
//}
