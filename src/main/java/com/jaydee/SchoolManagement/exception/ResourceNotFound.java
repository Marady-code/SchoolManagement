package com.jaydee.SchoolManagement.exception;

public class ResourceNotFound extends RuntimeException{
	
	private ResourceNotFound(String resourceName, Long id) {
		super(String.format("%s not found with id : %d", resourceName, id));
	}
}
