package com.pakgo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
		HttpStatus status = ex.getStatus();
		ErrorResponse errorResponse = new ErrorResponse(status.value(), ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse errorResponse = new ErrorResponse(status.value(), "An internal server error occurred.",
				LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, status);
	}

	@Data
	@AllArgsConstructor
	public static class ErrorResponse {
		private int status;
		private String message;
		private LocalDateTime timestamp;
	}
}