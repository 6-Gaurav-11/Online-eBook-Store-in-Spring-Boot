package com.capgemini.books.exception;

public class NoBooksAvailableException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBooksAvailableException() {
		super();
	}
	
	public NoBooksAvailableException(String msg) {
		super(msg);
	}

}
