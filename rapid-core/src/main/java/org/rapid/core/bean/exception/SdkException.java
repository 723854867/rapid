package org.rapid.core.bean.exception;

import org.rapid.core.bean.model.code.ICode;

public class SdkException extends BizException {

	private static final long serialVersionUID = -1413583914995227003L;

	private String errorCode;
	private String errorMessage;
	
	public SdkException() {}
	
	public SdkException(ICode code, String errorCode, String errorMessage) {
		super(code);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
