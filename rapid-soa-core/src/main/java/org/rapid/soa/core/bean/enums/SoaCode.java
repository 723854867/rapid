package org.rapid.soa.core.bean.enums;

import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;

public enum SoaCode implements ICode {
	
	AUTH_FAIL(200, "code.auth_fail", "访问未授权");

	private int code;
	private String key;
	private String desc;
	
	private SoaCode(int code, String key, String desc) {
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
		for (SoaCode code : SoaCode.values()) {
			if (code.key.equals(key))
				return code;
		}
		return Code.match(key);
	}
}
