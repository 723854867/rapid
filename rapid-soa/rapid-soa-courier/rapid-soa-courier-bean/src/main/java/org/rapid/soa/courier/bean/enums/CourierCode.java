package org.rapid.soa.courier.bean.enums;

import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;

public enum CourierCode implements ICode {
	
	CAPTCHA_OBTAIN_FREQ(300, "code.captcha.obtain.freq", "验证码获取太频繁"),
	CAPTCHA_OBTAIN_COUNT_LIMIT(301, "code.captcha.obtain.count.limit", "验证码获取次数限制");

	private int code;
	private String key;
	private String desc;
	
	private CourierCode(int code, String key, String desc) {
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
		for (CourierCode code : CourierCode.values()) {
			if (code.key.equals(key))
				return code;
		}
		return Code.match(key);
	}
}
