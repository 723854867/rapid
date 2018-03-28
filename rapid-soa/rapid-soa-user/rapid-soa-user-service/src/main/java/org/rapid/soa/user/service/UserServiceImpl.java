package org.rapid.soa.user.service;

import javax.annotation.Resource;

import org.rapid.core.Assert;
import org.rapid.core.RapidConfiguration;
import org.rapid.soa.user.api.UserService;
import org.rapid.soa.user.bean.entity.UserDevice;
import org.rapid.soa.user.bean.entity.UserInvitation;
import org.rapid.soa.user.bean.entity.Username;
import org.rapid.soa.user.bean.enums.UserCode;
import org.rapid.soa.user.bean.info.LoginInfo;
import org.rapid.soa.user.bean.request.LoginRequest;
import org.rapid.soa.user.bean.request.RegisterRequest;
import org.rapid.soa.user.internal.EntityGenerator;
import org.rapid.soa.user.internal.UserLocker;
import org.rapid.soa.user.internal.Consts.GlobalKeys;
import org.rapid.soa.user.manager.UserManager;
import org.rapid.util.bean.Pair;
import org.rapid.util.codec.Decrypt;
import org.rapid.util.codec.Encrypt;
import org.rapid.util.serialize.SerializeUtil;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserLocker userLocker;
	@Resource
	private UserManager userManager;
	
	@Override
	public String tryLock(long uid) {
		return userLocker.tryLock(uid);
	}
	
	@Override
	public Pair<Long, String> tryLock(String token) {
		String key = RapidConfiguration.get(GlobalKeys.USER_TOKEN_SECRET_KEY);
		String json = Decrypt.AESDecode(key, token);
		UserDevice device = SerializeUtil.GSON.fromJson(json, UserDevice.class);
		String lockId = tryLock(device.getUid());
		UserDevice odevice = userManager.device(device.getId());
		Assert.isTrue(UserCode.USER_UNLOGIN, null != odevice && odevice.getToken().equals(token) && odevice.getUid() == device.getUid());
		return new Pair<Long, String>(device.getUid(), lockId);
	}
	
	@Override
	public boolean releaseLock(long uid, String lockId) {
		return userLocker.releaseLock(uid, lockId);
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
		Pair<LoginInfo, UserDevice> result = userLocker.invoke(username.getUid(), () -> {
			return userManager.login(device, request.getPassword());
		});
		if (null != result.getValue()) {
			// TODO： 原设备挤下线推送
		}
		return result.getKey();
	}
}
