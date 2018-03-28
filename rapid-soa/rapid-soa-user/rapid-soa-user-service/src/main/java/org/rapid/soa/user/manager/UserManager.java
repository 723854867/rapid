package org.rapid.soa.user.manager;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.core.Assert;
import org.rapid.soa.user.bean.entity.UserDevice;
import org.rapid.soa.user.bean.entity.UserInfo;
import org.rapid.soa.user.bean.entity.UserInvitation;
import org.rapid.soa.user.bean.entity.Username;
import org.rapid.soa.user.bean.enums.UserCode;
import org.rapid.soa.user.bean.enums.UsernameType;
import org.rapid.soa.user.bean.info.LoginInfo;
import org.rapid.soa.user.bean.model.query.DeviceQuery;
import org.rapid.soa.user.bean.model.query.UsernameQuery;
import org.rapid.soa.user.bean.request.RegisterRequest;
import org.rapid.soa.user.dao.UserDeviceDao;
import org.rapid.soa.user.dao.UserInfoDao;
import org.rapid.soa.user.dao.UserInvitationDao;
import org.rapid.soa.user.dao.UsernameDao;
import org.rapid.soa.user.internal.EntityGenerator;
import org.rapid.util.Consts;
import org.rapid.util.bean.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserManager {

	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private UsernameDao usernameDao;
	@Resource
	private UserDeviceDao userDeviceDao;
	@Resource
	private UserInvitationDao userInvitationDao;

	@Transactional
	public Pair<Long, UserInvitation> register(RegisterRequest request) {
		UserInfo user = EntityGenerator.newUserInfo(request.getPassword());
		userInfoDao.insert(user);
		Username username = EntityGenerator.newUsername(user, request.getUsername(), request.getUsernameType());
		usernameDao.insert(username);
		Pair<Long, UserInvitation> pair = new Pair<Long, UserInvitation>();
		if (null != request.getInviter()) {
			UserInfo invitor = userInfoDao.getByKey(request.getInviter());
			Assert.notNull(UserCode.INVITOR_NOT_EXIST, invitor);
			UserInvitation invitation = EntityGenerator.newUserInvitation(invitor, user);
			userInvitationDao.insert(invitation);
			pair.setValue(invitation);
		}
		pair.setKey(user.getId());
		return pair;
	}
	
	@Transactional
	public Pair<LoginInfo, UserDevice> login(UserDevice device, String pwd) {
		UserInfo user = userInfoDao.getByKey(device.getUid());
		String cpwd = DigestUtils.md5Hex(pwd + Consts.Symbol.UNDERLINE + user.getSalt());
		Assert.isTrue(UserCode.LOGIN_PWD_ERROR, cpwd.equalsIgnoreCase(pwd));
		DeviceQuery query = new DeviceQuery();
		query.uid(user.getId()).type(device.getType());
		UserDevice odevice = userDeviceDao.queryUnique(query);
		Pair<LoginInfo, UserDevice> pair = new Pair<LoginInfo, UserDevice>();
		if (null != odevice) {							// 已经有同类型的设备登录了
			userDeviceDao.deleteByKey(odevice.getId());
			if (!odevice.getId().equals(device.getId())) 
				pair.setValue(odevice);
		}
		userDeviceDao.insert(device);
		pair.setKey(new LoginInfo(user.getId(), device.getToken()));
		return pair;
	}
	
	public Username username(UsernameType type, String username) {
		UsernameQuery query = new UsernameQuery();
		query.username(username).type(type);
		return usernameDao.queryUnique(query);
	}
	
	public UserDevice device(String id) {
		return userDeviceDao.getByKey(id);
	}
}
