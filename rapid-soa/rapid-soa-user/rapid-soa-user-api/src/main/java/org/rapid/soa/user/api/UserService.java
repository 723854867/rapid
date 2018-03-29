package org.rapid.soa.user.api;

import java.util.Set;

import org.rapid.soa.core.bean.model.User;
import org.rapid.soa.user.bean.info.LoginInfo;
import org.rapid.soa.user.bean.request.LoginRequest;
import org.rapid.soa.user.bean.request.RegisterRequest;

/**
 * 用户接口
 * 
 * @author lynn
 */
public interface UserService {

	User user(String token);

	// 获取用户拥有的角色列表
	Set<Integer> roles(long uid);

	// 通过 token 获取用户锁
	User lock(String token, long timeout);

	// 新建用户(注册)
	long register(RegisterRequest request);

	// 登录
	LoginInfo login(LoginRequest request);

	// 释放用户锁
	boolean releaseLock(long uid, String lockId);
}
