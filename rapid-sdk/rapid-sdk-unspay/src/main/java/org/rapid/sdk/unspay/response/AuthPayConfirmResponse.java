package org.rapid.sdk.unspay.response;

import org.rapid.core.http.HttpResponse;

import com.google.gson.annotations.SerializedName;

public class AuthPayConfirmResponse extends HttpResponse {

	private static final long serialVersionUID = 8444287767447183482L;

	@SerializedName("result_code")
	private String resultCode;
	@SerializedName("result_msg")
	private String resultMsg;
	private String status;
	private String desc;

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

	@Override
	public String code() {
		return this.resultCode;
	}

	@Override
	public String desc() {
		return success() ? this.resultMsg + " - [" + desc + "]" : this.resultMsg;
	}

	@Override
	public boolean success() {
		return this.resultCode.equals("0000");
	}

	/**
	 * 仅当 success 为 true 时才可以调用
	 */
	public boolean failure() {
		if (!success())
			throw new RuntimeException("银生宝请求失败，请求结果无效！");
		return this.status.equals("20");
	}
}
