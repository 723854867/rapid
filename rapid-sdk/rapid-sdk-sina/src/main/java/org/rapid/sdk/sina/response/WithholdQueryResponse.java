package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class WithholdQueryResponse extends SinaResponse {

	private static final long serialVersionUID = 1718152536892820648L;

	@SerializedName("is_withhold_authoity")
	private String isWithholdAuthoity;
	@SerializedName("withhold_authoity_time")
	private String withhold_authoity_time;
	@SerializedName("auth_list")
	private String authList;

	public String getIsWithholdAuthoity() {
		return isWithholdAuthoity;
	}

	public void setIsWithholdAuthoity(String isWithholdAuthoity) {
		this.isWithholdAuthoity = isWithholdAuthoity;
	}

	public String getWithhold_authoity_time() {
		return withhold_authoity_time;
	}

	public void setWithhold_authoity_time(String withhold_authoity_time) {
		this.withhold_authoity_time = withhold_authoity_time;
	}

	public String getAuthList() {
		return authList;
	}

	public void setAuthList(String authList) {
		this.authList = authList;
	}
	
	public boolean isWithholdAuthority() {
		return isWithholdAuthoity.equals("Y");
	}
}
