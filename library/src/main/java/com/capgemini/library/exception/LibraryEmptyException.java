package com.capgemini.library.exception;

public class LibraryEmptyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibraryEmptyException() {
		super();
	}
	
	public LibraryEmptyException(String msg) {
		super(msg);
	}

}
