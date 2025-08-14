package com.jaydee.SchoolManagement.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle custom ApiException
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponses> handleApiException(ApiException ex, WebRequest request) {
        ErrorResponses error = new ErrorResponses(
                LocalDateTime.now(),
                ex.getStatus(),
                ex.getStatus().getReasonPhrase(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    // Handle uncaught exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponses> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponses error = new ErrorResponses(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
