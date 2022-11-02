package com.capgemini.users.exception;

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
	
	@ExceptionHandler(NoUsersAvailableException.class)
	public ResponseEntity<String> handleNoBooks(NoUsersAvailableException e) {
		return new ResponseEntity<String>("No users are available.", HttpStatus.CONTINUE);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleNoBookOne(UserNotFoundException e) {
		return new ResponseEntity<String>("No user found with the given username.", HttpStatus.BAD_REQUEST);
	}
}
