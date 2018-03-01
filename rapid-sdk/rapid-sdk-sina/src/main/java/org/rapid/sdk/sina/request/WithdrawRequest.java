package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.WithdrawResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawRequest extends UserRequest<WithdrawResponse> {

	private static final long serialVersionUID = -1579603897977630775L;

	@Expose
	@SerializedName("out_trade_no")
	private String outTradeNo;
	@Expose
	private String summary;
	@Expose
	@SerializedName("account_type")
	private String accountType = "BASIC";
	@Expose
	private String amount;
	@Expose
	@SerializedName("user_fee")
	private String userFee;
	@Expose
	@SerializedName("card_id")
	private String cardId;
	@Expose
	@SerializedName("withdraw_mode")
	private String withdrawMode;
	// GENERAL-普通;FAST-快速
	@Expose
	@SerializedName("payto_type")
	private String paytoType = "GENERAL";
	@Expose
	@SerializedName("withdraw_close_time")
	private String withdrawCloseTime;
	@Expose
	@SerializedName("user_ip")
	private String userIp;

	public WithdrawRequest() {
		super("新浪托管提现", SinaConfig.getGateWayOrder());
		setService("create_hosting_withdraw");
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUserFee() {
		return userFee;
	}

	public void setUserFee(String userFee) {
		this.userFee = userFee;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getWithdrawMode() {
		return withdrawMode;
	}

	public void setWithdrawMode(String withdrawMode) {
		this.withdrawMode = withdrawMode;
	}

	public String getPaytoType() {
		return paytoType;
	}

	public void setPaytoType(String paytoType) {
		this.paytoType = paytoType;
	}

	public String getWithdrawCloseTime() {
		return withdrawCloseTime;
	}

	public void setWithdrawCloseTime(String withdrawCloseTime) {
		this.withdrawCloseTime = withdrawCloseTime;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
}
