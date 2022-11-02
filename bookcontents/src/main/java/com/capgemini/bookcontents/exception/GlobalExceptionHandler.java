package com.capgemini.bookcontents.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<String> handleNoBooks(BookNotFoundException e) {
		return new ResponseEntity<String>("Book not found for the given book ID.", HttpStatus.CONTINUE);
	}
	
	@ExceptionHandler(NoContentEntryException.class)
	public ResponseEntity<String> handleNoElement(NoContentEntryException e) {
		return new ResponseEntity<String>("Insufficient data, please provide complete data.", HttpStatus.BAD_REQUEST);
	}
	
}
