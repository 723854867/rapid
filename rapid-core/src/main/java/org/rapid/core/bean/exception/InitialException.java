package org.rapid.core.bean.exception;

public class InitialException extends RuntimeException {

	private static final long serialVersionUID = 349450456065272771L;
	
	public InitialException(String message) {
		super(message);
	}

	public InitialException(String message, Throwable cause) {
		super(message, cause);
	}
}
