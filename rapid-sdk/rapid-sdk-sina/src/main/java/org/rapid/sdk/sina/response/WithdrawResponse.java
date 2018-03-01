package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class WithdrawResponse extends SinaResponse {

	private static final long serialVersionUID = 6441399460144559088L;

	@SerializedName("out_trade_no")
	private String outTradeNo;
	@SerializedName("withdraw_status")
	private String withdrawStatus;
	// 当请求参数中的“version”的值是“1.1”时，且提现方式选择“CASHDESK”，此参数不为空。商户系统需要将用户按此参数的值重定向到新浪收银台。其他情况不返回此值，“version”的值是“1.0”时也不返回此值。
	@SerializedName("redirect_url")
	private String redirectUrl;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getWithdrawStatus() {
		return withdrawStatus;
	}

	public void setWithdrawStatus(String withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
