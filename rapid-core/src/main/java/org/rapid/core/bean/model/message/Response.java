package org.rapid.core.bean.model.message;

import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;
import org.rapid.util.StringUtil;

public class Response<T> implements Message {

	private static final long serialVersionUID = 4618491061991821038L;

	// 响应结果
	private T attach;
	// 错误码
	private int code;
	private String desc = "";
	private ICode codeRefer;
	// 请求id
	private String requestId;
	
	public Response() {}
	
	public Response(T attach) {
		this(Code.SUCCESS, attach);
	}
	
	public Response(ICode code) {
		this(code, null);
	}
	
	public Response(ICode code, T attach) {
		this.attach = attach;
		this.codeRefer = code;
		this.code = code.code();
	}

	public T getAttach() {
		return attach;
	}

	public void setAttach(T attach) {
		this.attach = attach;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc += StringUtil.hasText(this.desc) ? " - [" + desc + "]" : "[" + desc + "]";
	}
	
	public ICode getCodeRefer() {
		return codeRefer;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String identity() {
		return this.requestId;
	}
}
