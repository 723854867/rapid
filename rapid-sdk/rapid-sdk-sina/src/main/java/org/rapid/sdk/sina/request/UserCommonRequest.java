package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.enums.UserAction;
import org.rapid.sdk.sina.response.CommonResponse;

public class UserCommonRequest extends UserRequest<CommonResponse> {

	private static final long serialVersionUID = 5766839857739272751L;

	public UserCommonRequest(UserAction action) {
		super(action, SinaConfig.getGateWayMember());
		setService(action.service());
	}
}
