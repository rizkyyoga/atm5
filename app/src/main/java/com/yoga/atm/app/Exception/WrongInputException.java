package com.yoga.atm.app.Exception;

public class WrongInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 944258416548220120L;

	public WrongInputException(String message) {
		super(message);
	}

	public WrongInputException() {
		super();
	}
}
