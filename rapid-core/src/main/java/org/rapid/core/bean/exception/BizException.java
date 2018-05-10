package org.rapid.core.bean.exception;

import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;
import org.rapid.util.exception.RapidRuntimeException;

public class BizException extends RapidRuntimeException {

	private static final long serialVersionUID = -1644826526195500863L;

	private ICode code;
	
	public BizException() {
		this(Code.SYS_ERROR);
	}
	
	public BizException(ICode code) {
		this(code.desc());
		this.code = code;
	}
	
	public BizException(String message) {
		super(message);
		this.code = Code.SYS_ERROR;
	}
	
	public BizException(Throwable cause) {
		super(cause);
		this.code = Code.SYS_ERROR;
	}
	
	public BizException(ICode code, String message) {
		super(message);
		this.code = code;
	}
	
	public BizException(Throwable cause, String message) {
		super(message, cause);
		this.code = Code.SYS_ERROR;
	}
	
	public ICode getCode() {
		return code;
	}
	
	public void setCode(ICode code) {
		this.code = code;
	}
}
