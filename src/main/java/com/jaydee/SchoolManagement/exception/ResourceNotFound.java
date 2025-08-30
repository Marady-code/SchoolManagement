package com.jaydee.SchoolManagement.exception;

public class ResourceNotFound extends ApiException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFound(String resourceName, Object id) {
        super(ErrorCode.RESOURCE_NOT_FOUND,
                String.format("%s not found with id: %s", resourceName, id));
    }

    public ResourceNotFound(String resourceName, String field, Object value) {
        super(ErrorCode.RESOURCE_NOT_FOUND,
                String.format("%s not found with %s: %s", resourceName, field, value));
    }

    public ResourceNotFound(String message) {
        super(ErrorCode.RESOURCE_NOT_FOUND, message);
    }
}
