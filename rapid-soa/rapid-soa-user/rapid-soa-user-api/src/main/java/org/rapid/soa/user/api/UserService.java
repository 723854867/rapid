package org.rapid.soa.user.api;

import java.util.Set;

import org.rapid.soa.core.bean.entity.UserInfo;
import org.rapid.soa.user.bean.info.LoginInfo;
import org.rapid.soa.user.bean.request.LoginRequest;
import org.rapid.soa.user.bean.request.RegisterRequest;
import org.rapid.util.bean.Pair;

/**
 * 用户接口
 * 
 * @author lynn
 */
public interface UserService {
	
	UserInfo user(String token);
	
	// 通过 token 获取用户锁
	Pair<UserInfo, String> lock(String token, long timeout);
	
	// 释放用户锁
	boolean releaseLock(long uid, String lockId);

	// 新建用户(注册)
	long register(RegisterRequest request);
	
	// 登录
	LoginInfo login(LoginRequest request);
	
	// 获取用户拥有的权限模块
	Set<Integer> modulars(long uid);
}
