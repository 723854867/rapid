package org.rapid.util.exception;

public class RapidRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -5611559557332188406L;

	public RapidRuntimeException() {
		super();
	}

	public RapidRuntimeException(String message) {
		super(message);
	}
	
	public RapidRuntimeException(Throwable cause) {
		super(cause);
	}

	public RapidRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
