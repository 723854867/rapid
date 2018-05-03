package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class ModifyVerifyMobileResponse extends SinaResponse {

	private static final long serialVersionUID = -8664340460730920905L;
	@SerializedName("redirect_url")
	private String redirectUrl;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
