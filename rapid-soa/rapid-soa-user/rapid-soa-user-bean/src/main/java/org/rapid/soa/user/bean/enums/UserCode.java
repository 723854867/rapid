package org.rapid.soa.user.bean.enums;

import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;

public enum UserCode implements ICode {
	
	INVITOR_NOT_EXIST(300, "code.invitor.not.exist", "邀请人不存在"),
	USER_LOCK_FAIL(301, "code.user.lock.fail", "用户正在操作中"),
	USERNAME_NOT_EXIST(302, "code.username.not.exist", "用户名不存在"),
	LOGIN_PWD_ERROR(303, "code.login.pwd.error", "登录密码错误"),
	USER_UNLOGIN(304, "code.unlogin", "用户未登录");

	private int code;
	private String key;
	private String desc;
	
	private UserCode(int code, String key, String desc) {
		this.key = key;
		this.code = code;
		this.desc = desc;
	}

	@Override
	public int code() {
		return this.code;
	}

	@Override
	public String key() {
		return this.key;
	}

	@Override
	public String desc() {
		return this.desc;
	}
	
	public static final ICode match(String key) { 
		for (UserCode code : UserCode.values()) {
			if (code.key.equals(key))
				return code;
		}
		return Code.match(key);
	}
}
