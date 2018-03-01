package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class RechargeResponse extends SinaResponse {

	private static final long serialVersionUID = -1914541330119233010L;

	@SerializedName("out_trade_no")
	private String outTradeNo;
	@SerializedName("deposit_status")
	private String depositStatus;
	private String ticket;
	@SerializedName("trans_account_name")
	private String transAccountName;
	@SerializedName("trans_account_no")
	private String transAccountNo;
	@SerializedName("trans_bank_brank")
	private String transBankBrank;
	@SerializedName("trans_trade_no")
	private String transTradeNo;
	@SerializedName("redirect_url")
	private String redirectUrl;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(String depositStatus) {
		this.depositStatus = depositStatus;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getTransAccountName() {
		return transAccountName;
	}

	public void setTransAccountName(String transAccountName) {
		this.transAccountName = transAccountName;
	}

	public String getTransAccountNo() {
		return transAccountNo;
	}

	public void setTransAccountNo(String transAccountNo) {
		this.transAccountNo = transAccountNo;
	}

	public String getTransBankBrank() {
		return transBankBrank;
	}

	public void setTransBankBrank(String transBankBrank) {
		this.transBankBrank = transBankBrank;
	}

	public String getTransTradeNo() {
		return transTradeNo;
	}

	public void setTransTradeNo(String transTradeNo) {
		this.transTradeNo = transTradeNo;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
