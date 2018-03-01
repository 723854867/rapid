package org.rapid.util.exception;

/**
 * 加解密异常
 */
public class CryptException extends RapidRuntimeException {

	private static final long serialVersionUID = 2792800198975288477L;

	public CryptException(String message, Throwable cause) {
		super(message, cause);
	}
}
