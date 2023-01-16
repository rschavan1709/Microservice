package com.neosoft.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("hotel with given id does not exist");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
