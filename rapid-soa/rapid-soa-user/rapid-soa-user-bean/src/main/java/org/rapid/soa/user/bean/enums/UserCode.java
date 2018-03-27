package org.rapid.soa.user.bean.enums;

import org.rapid.core.bean.model.code.ICode;

public enum UserCode implements ICode {
	
	INVITOR_NOT_EXIST(200, "code.invitor.not.exist", "邀请人不存在");

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
	
	public static final UserCode match(String key) { 
		for (UserCode code : UserCode.values()) {
			if (code.key.equals(key))
				return code;
		}
		return null;
	}
}
