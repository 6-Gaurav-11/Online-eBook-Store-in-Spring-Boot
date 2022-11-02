package com.capgemini.books.exception;

public class BookNotAvailableException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotAvailableException() {
		super();
	}
	
	public BookNotAvailableException(String msg) {
		super(msg);
	}

}
