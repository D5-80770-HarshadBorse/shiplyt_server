package com.shiplyt.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {

	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException(String message, HttpStatus status) {
		super(message, status);
	}
}
