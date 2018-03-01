package org.rapid.util.exception;

public class RapidException extends Exception {

	private static final long serialVersionUID = 1517858285114637273L;

	public RapidException() {
		super();
	}

	public RapidException(String message) {
		super(message);
	}
	
	public RapidException(Throwable cause) {
		super(cause);
	}

	public RapidException(String message, Throwable cause) {
		super(message, cause);
	}
}
