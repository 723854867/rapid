package org.rapid.sdk.unspay.response;

import org.rapid.core.http.HttpResponse;

import com.google.gson.annotations.SerializedName;

public class AuthPayRechargeResponse extends HttpResponse {

	private static final long serialVersionUID = 8594250773396685551L;

	@SerializedName("result_code")
	private String resultCode;
	@SerializedName("result_msg")
	private String resultMsg;
	private String token;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String code() {
		return this.resultCode;
	}

	@Override
	public String desc() {
		return this.resultMsg;
	}
	
	@Override
	public boolean success() {
		return this.resultCode.equals("0000");
	}
}
