package com.capgemini.users.exception;

public class NoUsersAvailableException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUsersAvailableException() {
		super();
	}
	
	public NoUsersAvailableException(String msg) {
		super(msg);
	}

}
