package org.rapid.service.manager;

import javax.annotation.Resource;

import org.rapid.service.mybatis.dao.UserDeviceDao;
import org.rapid.service.mybatis.dao.UserInfoDao;
import org.rapid.service.mybatis.dao.UsernameDao;
import org.springframework.stereotype.Component;

@Component
public class RapidUserManager {

	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private UsernameDao usernameDao;
	@Resource
	private UserDeviceDao userDeviceDao;
	
	// 注册用户
	public void register() {
		
	}

	// 用户登录
	public void login() {
		
	}
}
