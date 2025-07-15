package com.jaydee.SchoolManagement.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFound extends ApiException{
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFound(String resourceName, Long id) {
		super(HttpStatus.NOT_FOUND,String.format("%s not found with id : %d", resourceName, id));
	}

	public ResourceNotFound(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
