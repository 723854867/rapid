package org.rapid.core.bean.model.message;

/**
 * 请求
 * 
 * @author lynn
 */
public interface Request extends Message {

	String ip();
	
	String requestId();
	
	@Override
	default String identity() {
		return requestId();
	}
	
	/**
	 * 基本的参数验证，返回错误描述，如果返回为 null 则表示验证成功
	 */
	default void verify() {
	}
}
