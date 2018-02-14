package org.rapid.core.bean.model.code;

public enum Code implements ICode {
	
	SUCCESS(0, "code.success", "成功"),
	SYS_ERROR(1, "code.sys.error", "系统错误"),
	PARAM_ERROR(2, "code.param.error", "参数错误"),
	FORBID(3, "code.forbid", "非法访问"),
	SERVER_BUSY(10, "code.server.busy", "服务器繁忙"),
	
	CAPTCHA_OBTAIN_FREQ(20, "code.captcha.obtain.freq", "验证码获取太频繁"),
	CAPTCHA_OBTAIN_COUNT_LIMIT(21, "code.captcha.obtain.count.limit", "验证码获取次数限制");
	
	private int code;
	private String key;
	private String desc;
	
	private Code(int code, String key, String desc) {
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
	
	public static final Code match(String key) { 
		for (Code code : Code.values()) {
			if (code.key.equals(key))
				return code;
		}
		return null;
	}
}
