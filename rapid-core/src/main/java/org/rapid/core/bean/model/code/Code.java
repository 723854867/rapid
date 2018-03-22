package org.rapid.core.bean.model.code;

public enum Code implements ICode {
	
	SUCCESS(0, "code.success", "成功"),
	SYS_ERROR(1, "code.sys.error", "系统错误"),
	PARAM_ERROR(2, "code.param.error", "参数错误"),
	FORBID(3, "code.forbid", "非法访问"),
	SERVER_BUSY(10, "code.server.busy", "服务器繁忙"),
	
	CAPTCHA_OBTAIN_FREQ(20, "code.captcha.obtain.freq", "验证码获取太频繁"),
	CAPTCHA_OBTAIN_COUNT_LIMIT(21, "code.captcha.obtain.count.limit", "验证码获取次数限制"),
	
	UNSUPPORT_CONTENT_TYPE(50, "code.unsupport.content.type", "不支持的 ContentType"),
	UNSUPPORT_HTTP_METHOD(51, "code.unsupport.http.method", "不支持的HTTP请求方法"),
	UPLOAD_SIZE_EXCEEDED(52, "code.upload.size.exceeded", "上传文件太大"),
	UPLOAD_COUNT_EXCEEDED(53, "code.upload.count.exceeded", "上传文件数量超过限制"),
	
	USER_NOT_EXIST(100, "code.user.not.exist", "用户不存在"),
	PASSWORD_ERROR(101, "code.password.error", "密码错误"),
	CAPTCHA_ERROR(102, "code.captcha.error", "验证码错误"),
	KEY_DUPLICATED(103, "code.data.exist", "主键冲突,请重试"),
	RESOURCE_LOCKED(104, "code.resource.locked", "资源已被占用"),
	DATA_CHANGED(105, "code.data.changed", "数据已改变"),
	UNLOGIN(110, "code.unlogin", "未登录"),
	TOKEN_INVALID(111, "code.token.invalid", "Token 已失效"),
	UNREALNAME(112, "code.unrealname", "用户未实名"),
	USABLE_LACK(120, "code.money.lack", "可用余额不足"),
	FROZEN_LACK(121, "code.frozen.lack", "冻结余额不足"),
	SYS_CONFIG_ERROR(122, "code.sys.config.error", "系统配置错误"),
	SYS_CONFIG_NOT_EXIST(123, "code.sys.config.not.exist", "系统配置不存在"),
	
	COMPANY_NOT_EXIST(130, "code.company.not.exist", "公司不存在"),
	
	NOTICE_SIGN_VERIFY_FAILURE(131, "code.notice.sign.verify.failure", "通知验签失败");
	
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
