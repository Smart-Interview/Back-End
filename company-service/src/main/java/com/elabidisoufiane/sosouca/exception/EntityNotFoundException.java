package com.elabidisoufiane.sosouca.exception;

public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException() {

	}

	public EntityNotFoundException(String message) {
		super(message);
	}
}
