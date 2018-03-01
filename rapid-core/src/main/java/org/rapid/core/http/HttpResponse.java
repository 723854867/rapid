package org.rapid.core.http;

import java.io.Serializable;

public interface HttpResponse extends Serializable {

	/**
	 * 请求错误码
	 */
	String code();
	
	/**
	 * 请求错误描述
	 */
	String desc();
	
	/**
	 * 判断请求是否成功
	 */
	boolean success();
}
