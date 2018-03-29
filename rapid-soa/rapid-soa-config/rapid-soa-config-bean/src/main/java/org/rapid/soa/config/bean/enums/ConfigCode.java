package org.rapid.soa.config.bean.enums;

import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;

public enum ConfigCode implements ICode {
	
	MODULAR_NOT_EXIST(500, "code.modular.not.exist", "模块不存在"),
	GATEWAY_NOT_EXIST(501, "code.gateway.not.exist", "网关不存在"),
	ROLE_NOT_EXIST(502, "code.role.not.exist", "角色不存在");

	private int code;
	private String key;
	private String desc;
	
	private ConfigCode(int code, String key, String desc) {
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
		for (ConfigCode code : ConfigCode.values()) {
			if (code.key.equals(key))
				return code;
		}
		return Code.match(key);
	}
}
