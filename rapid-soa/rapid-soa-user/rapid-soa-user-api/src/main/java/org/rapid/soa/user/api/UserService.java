package org.rapid.soa.user.api;

import java.util.Set;

import org.rapid.soa.core.bean.model.User;
import org.rapid.soa.user.bean.info.LoginInfo;
import org.rapid.soa.user.bean.request.LoginRequest;
import org.rapid.soa.user.bean.request.RegisterRequest;
import org.rapid.soa.user.bean.request.UnauthRequest;
import org.rapid.soa.user.bean.request.AuthRequest;

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
	
	/**
	 * 用户授权
	 * <pre>
	 * 1、操作用户本身要有授权权限，或者是root用户
	 * 2、如果授权的同时把授权权限也赋予给目标用户，则目标用户也可以授权
	 * 3、用户授权只能授予目标自身有用的权限
	 * 4、root用户可以操作所有角色的授权
	 * </pre>
	 */
	long auth(AuthRequest request, boolean root);
	
	/**
	 * 取消授权：
	 * <pre>
	 * 1、操作用户本身要有取消授权的权限，或者是root用户
	 * 2、取消的授权只能是自己赋予给目标或者是自身的子孙(直接目标授予出去的权限)
	 * 3、root用户可以回收所有角色，回收也是级联的
	 * </pre>
	 */
	void unauth(UnauthRequest request, boolean root);
}
