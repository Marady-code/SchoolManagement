package com.jaydee.SchoolManagement.exception;

import java.util.Map;

public class ValidationException extends ApiException {

    private static final long serialVersionUID = 1L;
    private final Map<String, String> validationErrors;

    public ValidationException(String message) {
        super(ErrorCode.VALIDATION_ERROR, message);
        this.validationErrors = null;
    }

    public ValidationException(String message, Map<String, String> validationErrors) {
        super(ErrorCode.VALIDATION_ERROR, message);
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}

