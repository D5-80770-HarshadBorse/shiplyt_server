package com.shiplyt.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message, HttpStatus status) {
		super(message, status);
	}
}
