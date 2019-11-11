package com.yoga.atm.app.Exception;

public class TransactionFailureException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1732748715616974572L;

	public TransactionFailureException(String message) {
		super(message);
	}

	public TransactionFailureException() {
		super();
	}
}
