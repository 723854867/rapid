package org.rapid.soa.user.api;

import org.rapid.core.bean.model.request.RegisterRequest;

/**
 * 用户接口
 * 
 * @author lynn
 */
public interface UserService {

	// 新建用户(注册)
	long register(RegisterRequest request);
}
