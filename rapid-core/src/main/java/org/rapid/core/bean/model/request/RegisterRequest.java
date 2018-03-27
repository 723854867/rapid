package org.rapid.core.bean.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.rapid.core.Assert;
import org.rapid.core.bean.enums.UsernameType;
import org.rapid.core.bean.model.code.Code;
import org.rapid.util.PhoneUtil;

public class RegisterRequest extends RapidRequest {

	private static final long serialVersionUID = 4433642237759495470L;

	@Min(1)
	private Long inviter;
	// 验证码
	private String captcha;
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotNull
	private UsernameType usernameType;
	
	@Override
	public void verify() {
		super.verify();
		switch (usernameType) {
		case EMAIL:
			Assert.hasText(Code.PARAM_ERROR, captcha);
			break;
		case MOBILE:
			Assert.hasText(Code.PARAM_ERROR, captcha);
			this.username = PhoneUtil.parseMobile(username);
			break;
		default:
			break;
		}
	}
}
