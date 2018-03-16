package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class CreateSingleHostingPayTradeResponse extends SinaResponse {

	private static final long serialVersionUID = 2755279791726498682L;

	@SerializedName("out_trade_no")
	private String outTradeNo;
	@SerializedName("trade_status")
	private String tradeStatus;

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
}
