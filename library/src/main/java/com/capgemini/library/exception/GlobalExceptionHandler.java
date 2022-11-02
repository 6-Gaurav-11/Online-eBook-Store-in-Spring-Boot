package com.capgemini.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException e) {
		return new ResponseEntity<String>("Input fields is/are empty.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<String> handleNoBooks(BookNotFoundException e) {
		return new ResponseEntity<String>("No book found with the given book ID.", HttpStatus.CONTINUE);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleNoBookOne(UserNotFoundException e) {
		return new ResponseEntity<String>("No user found with the given username.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LibraryEmptyException.class)
	public ResponseEntity<String> handleNoBookOne(LibraryEmptyException e) {
		return new ResponseEntity<String>("Library is empty.", HttpStatus.BAD_REQUEST);
	}

}
