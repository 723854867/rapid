package org.rapid.soa.user.service;

import javax.annotation.Resource;

import org.rapid.core.bean.model.request.RegisterRequest;
import org.rapid.soa.user.api.UserService;
import org.rapid.soa.user.manager.UserManager;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserManager userManager;

	@Override
	public long register(RegisterRequest request) {
		return userManager.register(request);
	}
}
