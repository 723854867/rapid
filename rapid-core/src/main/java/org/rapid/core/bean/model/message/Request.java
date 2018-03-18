package org.rapid.core.bean.model.message;

/**
 * 请求
 * 
 * @author lynn
 */
public interface Request extends Message {

	/**
	 * 基本的参数验证，返回错误描述，如果返回为 null 则表示验证成功
	 */
	default void verify() {
	}
	
	@Override
	default String identity() {
		return null;
	}
}
