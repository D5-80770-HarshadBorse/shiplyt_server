package com.pakgo.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends BaseException {

	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistsException(String message, HttpStatus status) {
		super(message, status);
	}

}
