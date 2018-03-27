package org.rapid.soa.user.manager;

import javax.annotation.Resource;

import org.rapid.core.Assert;
import org.rapid.core.bean.model.request.RegisterRequest;
import org.rapid.soa.user.bean.entity.UserInfo;
import org.rapid.soa.user.bean.entity.UserInvitation;
import org.rapid.soa.user.bean.entity.Username;
import org.rapid.soa.user.bean.enums.UserCode;
import org.rapid.soa.user.dao.UserInfoDao;
import org.rapid.soa.user.dao.UserInvitationDao;
import org.rapid.soa.user.dao.UsernameDao;
import org.rapid.soa.user.util.EntityGenerator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserManager {
	
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private UsernameDao usernameDao;
	@Resource
	private UserInvitationDao userInvitationDao;

	@Transactional
	public long register(RegisterRequest request) {
		UserInfo user = EntityGenerator.newUserInfo(request.getPassword());
		userInfoDao.insert(user);
		Username username = EntityGenerator.newUsername(user, request.getUsername(), request.getUsernameType());
		usernameDao.insert(username);
		if (null != request.getInviter()) {
			UserInfo invitor = userInfoDao.getByKey(request.getInviter());
			Assert.notNull(UserCode.INVITOR_NOT_EXIST, invitor);
			UserInvitation invitation = EntityGenerator.newUserInvitation(invitor, user);
			userInvitationDao.insert(invitation);
		}
		return user.getId();
	}
}
