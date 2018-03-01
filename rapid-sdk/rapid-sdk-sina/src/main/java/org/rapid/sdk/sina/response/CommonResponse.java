package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class CommonResponse extends SinaResponse {

	private static final long serialVersionUID = 2208463998777558755L;

	@SerializedName("redirect_url")
	private String redirectUrl;
	@SerializedName("is_set_paypass")
	private String isSetPaypass;
	
	public String getRedirectUrl() {
		return redirectUrl;
	}
	
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	public String getIsSetPaypass() {
		return isSetPaypass;
	}
	
	public void setIsSetPaypass(String isSetPaypass) {
		this.isSetPaypass = isSetPaypass;
	}
	
	public boolean isSetPayPass() {
		return null == isSetPaypass ? false : isSetPaypass.equals("Y");
	}
}
