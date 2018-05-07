package org.rapid.sdk.sina.request;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;

import org.rapid.core.IDWorker;
import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.request.so.PayMethod;
import org.rapid.sdk.sina.response.RechargeResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeRequest extends UserRequest<RechargeResponse> {

	private static final long serialVersionUID = -5566897669754307036L;

	@Expose
	@SerializedName("out_trade_no")
	private String outTradeNo;
	@Expose
	private String summary;
	@Expose
	@SerializedName("account_type")
	private String accountType;
	@Expose
	private String amount;
	@Expose
	@SerializedName("user_fee")
	private String userFee;
	@Expose
	@SerializedName("payer_ip")
	private String payerIp;
	// 设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭取值范围：15m～15h。m-分钟，h-小时不接受小数点
	@Expose
	@SerializedName("deposit_close_time")
	private String depositCloseTime;
	@Expose
	@SerializedName("pay_method")
	private String payMethod;
	@Expose
	@SerializedName("cashdesk_addr_category")
	private String cashdeskAddrCategory = "MOBILE";

	public RechargeRequest() {
		super("新浪托管充值", SinaConfig.GATEWAY_ORDER.getDefaultValue());
		setService("create_hosting_deposit");
		this.outTradeNo = IDWorker.INSTANCE.nextSid();
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

	public String getUserFee() {
		return userFee;
	}

	public void setUserFee(String userFee) {
		this.userFee = userFee;
	}

	public String getPayerIp() {
		return payerIp;
	}

	public void setPayerIp(String payerIp) {
		this.payerIp = payerIp;
	}

	public String getDepositCloseTime() {
		return depositCloseTime;
	}

	public void setDepositCloseTime(String depositCloseTime) {
		this.depositCloseTime = depositCloseTime;
	}

	public String getPayMethod() {
		return payMethod;
	}
	
	public String getCashdeskAddrCategory() {
		return cashdeskAddrCategory;
	}
	
	public void setCashdeskAddrCategory(String cashdeskAddrCategory) {
		this.cashdeskAddrCategory = cashdeskAddrCategory;
	}

	public void amount(PayMethod payMethod, BigDecimal amount) {
		amount = amount.setScale(2, RoundingMode.UP);
		this.amount = amount.toString();
		this.payMethod = MessageFormat.format(payMethod.toString(), this.amount);
	}
}
