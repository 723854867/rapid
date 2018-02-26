package org.rapid.sdk.unspay.response;

import org.rapid.core.http.HttpResponse;

import com.google.gson.annotations.SerializedName;

public class AuthPayQueryResponse extends HttpResponse {

	private static final long serialVersionUID = 3013526871033686464L;

	@SerializedName("result_code")
	private String resultCode;
	@SerializedName("result_msg")
	private String resultMsg;
	private String status;
	private String desc;
	private String amount;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String code() {
		return this.resultCode;
	}

	@Override
	public String desc() {
		return success() ? this.resultMsg + " - [" + desc + "]" + " - [" + amount + "]" : this.resultMsg;
	}

	@Override
	public boolean success() {
		return this.resultCode.equals("0000");
	}
	
	public boolean fail() {
		if (!success())
			throw new RuntimeException("银生宝请求失败，请求结果无效！");
		return status.equals("20");
	}
}
