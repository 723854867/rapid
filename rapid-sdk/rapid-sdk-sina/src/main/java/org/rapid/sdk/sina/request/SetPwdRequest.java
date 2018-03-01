package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CommonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 设置支付密码重定向
 */
public class SetPwdRequest extends UserRequest<CommonResponse> {

	private static final long serialVersionUID = -319835493407987929L;
	
	@Expose
	@SerializedName("withhold_param")
	private String withholdParam;

	public SetPwdRequest() {
		super("新浪设置支付密码重定向", SinaConfig.getGateWayMember());
		setService("set_pay_password");
	}

	
	public String getWithholdParam() {
		return withholdParam;
	}
	
	public void setWithholdParam(String withholdParam) {
		this.withholdParam = withholdParam;
	}
}
