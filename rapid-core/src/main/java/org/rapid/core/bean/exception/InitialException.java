package org.rapid.core.bean.exception;

import org.rapid.util.exception.RapidRuntimeException;

public class InitialException extends RapidRuntimeException {

	private static final long serialVersionUID = 349450456065272771L;
	
	public InitialException(String message) {
		super(message);
	}

	public InitialException(String message, Throwable cause) {
		super(message, cause);
	}
}
