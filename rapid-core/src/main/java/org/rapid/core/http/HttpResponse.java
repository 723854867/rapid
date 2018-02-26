package org.rapid.core.http;

import java.io.Serializable;

public abstract class HttpResponse implements Serializable {

	private static final long serialVersionUID = 1667745194991898118L;
	
	/**
	 * 请求错误码
	 */
	public abstract String code();
	
	/**
	 * 请求错误描述
	 */
	public abstract String desc();
	
	/**
	 * 判断请求是否成功
	 */
	public abstract boolean success();
}
