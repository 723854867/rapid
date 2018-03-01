package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class CreateCollectResponse extends SinaResponse {

	private static final long serialVersionUID = -6425888103990855749L;

	@SerializedName("out_trade_no")
	private String outTradeNo;
	@SerializedName("trade_status")
	private String tradeStatus;
	@SerializedName("pay_status")
	private String payStatus;
	private String ticket;
	// 当请求参数中的“version”的值是“1.1”时，且支付方式扩展是网银并选择“SINAPAY”跳转新浪收银台时，此参数不为空。
	// 商户系统需要将用户按此参数的值重定向到新浪收银台。其他情况不返回此值，“version”的值是“1.0”时也不返回此值。
	@SerializedName("redirect_url")
	private String redirectUrl;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
