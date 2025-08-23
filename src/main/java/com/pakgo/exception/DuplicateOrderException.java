package com.pakgo.exception;

import org.springframework.http.HttpStatus;

public class DuplicateOrderException extends BaseException {

	private static final long serialVersionUID = 1L;

	public DuplicateOrderException(String message, HttpStatus status) {
		super(message, status);
	}
}
