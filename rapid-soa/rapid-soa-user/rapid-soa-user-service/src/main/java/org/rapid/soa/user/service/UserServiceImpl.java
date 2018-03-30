package org.rapid.soa.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.rapid.core.Assert;
import org.rapid.core.RapidConfiguration;
import org.rapid.soa.core.bean.entity.UserDevice;
import org.rapid.soa.core.bean.model.User;
import org.rapid.soa.user.api.UserService;
import org.rapid.soa.user.bean.entity.UserInvitation;
import org.rapid.soa.user.bean.entity.UserRole;
import org.rapid.soa.user.bean.entity.Username;
import org.rapid.soa.user.bean.enums.UserCode;
import org.rapid.soa.user.bean.info.LoginInfo;
import org.rapid.soa.user.bean.request.AuthRequest;
import org.rapid.soa.user.bean.request.LoginRequest;
import org.rapid.soa.user.bean.request.RegisterRequest;
import org.rapid.soa.user.bean.request.UnauthRequest;
import org.rapid.soa.user.internal.Consts.GlobalKeys;
import org.rapid.soa.user.internal.EntityGenerator;
import org.rapid.soa.user.internal.ThreadsafeInvoker;
import org.rapid.soa.user.manager.UserManager;
import org.rapid.util.bean.Pair;
import org.rapid.util.codec.Decrypt;
import org.rapid.util.codec.Encrypt;
import org.rapid.util.serialize.SerializeUtil;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserManager userManager;
	@Resource
	private ThreadsafeInvoker threadsafeInvoker;
	
	@Override
	public User user(String token) {
		String key = RapidConfiguration.get(GlobalKeys.USER_TOKEN_SECRET_KEY);
		String json = Decrypt.AESDecode(key, token);
		UserDevice device = SerializeUtil.GSON.fromJson(json, UserDevice.class);
		UserDevice odevice = userManager.device(device.getId());
		Assert.isTrue(UserCode.USER_UNLOGIN, null != odevice && odevice.getToken().equals(token) && odevice.getUid() == device.getUid());
		return new User(userManager.user(device.getUid()), device);
	}

	@Override
	public Set<Integer> roles(long uid) {
		Set<Integer> set = new HashSet<Integer>();
		List<UserRole> roles = userManager.roles(uid);
		roles.forEach(item -> set.add(item.getRoleId()));
		return set;
	}
	
	@Override
	public User lock(String token, long timeout) {
		String key = RapidConfiguration.get(GlobalKeys.USER_TOKEN_SECRET_KEY);
		String json = Decrypt.AESDecode(key, token);
		UserDevice device = SerializeUtil.GSON.fromJson(json, UserDevice.class);
		String lockId = 0 == timeout ? threadsafeInvoker.tryLock(device.getUid()) : threadsafeInvoker.lock(device.getUid(), timeout);
		UserDevice odevice = userManager.device(device.getId());
		Assert.isTrue(UserCode.USER_UNLOGIN, null != odevice && odevice.getToken().equals(token) && odevice.getUid() == device.getUid());
		return new User(lockId, userManager.user(device.getUid()), device);
	}
	
	@Override
	public boolean releaseLock(long uid, String lockId) {
		return threadsafeInvoker.releaseLock(uid, lockId);
	}

	@Override
	public long register(RegisterRequest request) {
		Pair<Long, UserInvitation> result = userManager.register(request);
		if (null != result.getValue()) {
			//TODO:处理邀请逻辑
		}
		return result.getKey();
	}
	
	@Override
	public LoginInfo login(LoginRequest request) {
		Username username = userManager.username(request.getUsernameType(), request.getUsername());
		Assert.notNull(UserCode.USERNAME_NOT_EXIST, username);
		UserDevice device = EntityGenerator.newUserDevice(username.getUid(), request.getDeviceType(), request.getDeviceId());
		String key = RapidConfiguration.get(GlobalKeys.USER_TOKEN_SECRET_KEY);
		String token = Encrypt.AESEncode(key, SerializeUtil.GSON.toJson(device));
		device.setToken(token);
		Pair<LoginInfo, UserDevice> result = threadsafeInvoker.invoke(username.getUid(), () -> {
			return userManager.login(device, request.getPassword());
		});
		if (null != result.getValue()) {
			// TODO： 原设备挤下线推送
		}
		return result.getKey();
	}
	
	@Override
	public long auth(AuthRequest request, boolean root) {
		return userManager.auth(request, root);
	}
	
	@Override
	public void unauth(UnauthRequest request, boolean root) {
		
	}
}
