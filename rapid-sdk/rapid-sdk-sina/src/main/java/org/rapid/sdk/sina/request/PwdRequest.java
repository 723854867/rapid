package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.enums.UserAction;
import org.rapid.sdk.sina.response.CommonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 设置支付密码重定向
 */
public class PwdRequest extends UserRequest<CommonResponse> {

	private static final long serialVersionUID = -319835493407987929L;
	
	private UserAction action;
	@Expose
	@SerializedName("withhold_param")
	private String withholdParam;

	public PwdRequest(UserAction action) {
		super(action, SinaConfig.getGateWayMember());
	}

	public UserAction getAction() {
		return action;
	}
	
	public void setAction(UserAction action) {
		this.action = action;
	}
	
	public String getWithholdParam() {
		return withholdParam;
	}
	
	public void setWithholdParam(String withholdParam) {
		this.withholdParam = withholdParam;
	}
}
