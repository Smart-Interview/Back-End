package com.elabidisoufiane.sosouca.exception;

public class EntityAlreadyExistsException extends RuntimeException {

	public EntityAlreadyExistsException() {

	}

	public EntityAlreadyExistsException(String message) {
		super(message);
	}
}
