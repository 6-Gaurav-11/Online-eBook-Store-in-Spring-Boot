package com.capgemini.books.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException e) {
		return new ResponseEntity<String>("Input fields is/are empty", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoBooksAvailableException.class)
	public ResponseEntity<String> handleNoBooks(NoBooksAvailableException e) {
		return new ResponseEntity<String>("No books are available right now", HttpStatus.CONTINUE);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoElement(NoSuchElementException e) {
		return new ResponseEntity<String>("No book found", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookNotAvailableException.class)
	public ResponseEntity<String> handleNoBookOne(BookNotAvailableException e) {
		return new ResponseEntity<String>("No book found with the given id", HttpStatus.BAD_REQUEST);
	}
}
