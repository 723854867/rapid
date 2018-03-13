package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CommonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithholdAuthorityRequest extends UserRequest<CommonResponse> {

	private static final long serialVersionUID = 6744484494144190125L;

	// 授权可设置的最小单笔额度，++为无限大
	@Expose
	private String quota = "++";
	// 授权可设置的最小日累计额度，++为无限大
	@Expose
	@SerializedName("day_quota")
	private String dayQuota = "++";
	// 以逗号分隔，期望的授权类型，目前可用的授权类型为ALL:银行卡授权(该授权包含账户授权)ACCOUNT,账户授权
	@Expose
	@SerializedName("auth_type_whitelist")
	private String authTypeWhitelist = "ACCOUNT";
	// 收银台地址类型，目前只包含MOBILE。为空时默认返回PC版页面，当传值为"MOBILE"时返回移动版页面。
	@Expose
	@SerializedName("cashdesk_addr_category")
	private String cashdeskAddrCategory = "MOBILE";

	public WithholdAuthorityRequest() {
		super("新浪委托扣款重定向", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("handle_withhold_authority");
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getDayQuota() {
		return dayQuota;
	}

	public void setDayQuota(String dayQuota) {
		this.dayQuota = dayQuota;
	}

	public String getAuthTypeWhitelist() {
		return authTypeWhitelist;
	}

	public void setAuthTypeWhitelist(String authTypeWhitelist) {
		this.authTypeWhitelist = authTypeWhitelist;
	}

	public String getCashdeskAddrCategory() {
		return cashdeskAddrCategory;
	}

	public void setCashdeskAddrCategory(String cashdeskAddrCategory) {
		this.cashdeskAddrCategory = cashdeskAddrCategory;
	}
}
