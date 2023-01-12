package com.neosoft.exception;

public class ResourceNotFoundException extends RuntimeException{

	
	public ResourceNotFoundException() {
		super("resource not found");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
