package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.ModifyVerifyMobileResponse;

public class ModifyVerifyMobileRequest extends UserRequest<ModifyVerifyMobileResponse> {

	private static final long serialVersionUID = 9210662870668142783L;

	public ModifyVerifyMobileRequest() {
		super("新浪修改认证手机", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("modify_verify_mobile");
	}

}
