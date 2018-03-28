package org.rapid.soa.user.api;

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
	
	// 获取用户锁，一旦获取成功则之后改用户的所有操作都是线程安全的
	String tryLock(long uid);
	
	// 通过 token 获取用户锁
	Pair<Long, String> tryLock(String token);
	
	// 释放用户锁
	boolean releaseLock(long uid, String lockId);

	// 新建用户(注册)
	long register(RegisterRequest request);
	
	// 登录
	LoginInfo login(LoginRequest request);
}
