package org.rapid.soa.user.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.core.IDWorker;
import org.rapid.core.bean.enums.UsernameType;
import org.rapid.soa.user.bean.entity.UserInfo;
import org.rapid.soa.user.bean.entity.UserInvitation;
import org.rapid.soa.user.bean.entity.Username;
import org.rapid.util.Consts.Symbol;
import org.rapid.util.DateUtil;
import org.rapid.util.KeyUtil;
import org.rapid.util.StringUtil;

public class EntityGenerator {

	public static final UserInfo newUserInfo(String password) {
		UserInfo instance = new UserInfo();
		instance.setAvatar(StringUtil.EMPTY);
		instance.setNickname(StringUtil.EMPTY);
		instance.setId(IDWorker.INSTANCE.nextId());
		instance.setSalt(KeyUtil.randomCode(6, false));
		instance.setPwd(DigestUtils.md5Hex(password + Symbol.UNDERLINE + instance.getSalt()));
		int time = DateUtil.current();
		instance.setCreated(time);
		instance.setUpdated(time);
		return instance;
	}
	
	public static final Username newUsername(UserInfo user, String username, UsernameType type) {
		Username instance = new Username();
		instance.setUid(user.getId());
		instance.setType(type.mark());
		instance.setUsername(username);
		int time = DateUtil.current();
		instance.setCreated(time);
		instance.setUpdated(time);
		return instance;
	}
	
	public static final UserInvitation newUserInvitation(UserInfo invitor, UserInfo invitee) {
		UserInvitation instance = new UserInvitation();
		instance.setInvitee(invitee.getId());
		instance.setInvitor(invitor.getId());
		instance.setCreated(DateUtil.current());
		return instance;
	}
}
