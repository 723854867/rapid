package org.rapid.sdk.unspay.response;

import org.rapid.core.http.HttpResponse;

import com.google.gson.annotations.SerializedName;

public class AuthPayRecodeResponse implements HttpResponse {

	private static final long serialVersionUID = -6881353293964883445L;
	
	@SerializedName("result_code")
	private String resultCode;
	@SerializedName("result_msg")
	private String resultMsg;

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
