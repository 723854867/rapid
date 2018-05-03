package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.FindVerifyMobileResponse;

public class FindVerifyMobileRequest extends UserRequest<FindVerifyMobileResponse> {

	private static final long serialVersionUID = 9210662870668142783L;

	public FindVerifyMobileRequest() {
		super("新浪忘记认证手机", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("find_verify_mobile");
	}

}
