package com.capgemini.users.exception;

public class EmptyInputException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyInputException() {
		super();
	}
	
	public EmptyInputException(String msg) {
		super(msg);
	}

}
