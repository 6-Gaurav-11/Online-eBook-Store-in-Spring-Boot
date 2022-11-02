package com.capgemini.library.exception;

public class UserNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
