package org.rapid.sdk.sina.request;

import org.rapid.core.IDWorker;
import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.SinaResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceUnfreezeRequest extends UserRequest<SinaResponse> {

	private static final long serialVersionUID = -5547242811378243170L;

	// 解冻订单号
	@Expose
	@SerializedName("out_unfreeze_no")
	private String outUnfreezeNo;
	// 原冻结订单号
	@Expose
	@SerializedName("out_freeze_no")
	private String outFreezeNo;
	// 可以为空：为空表示全额解冻，可以指定解冻部分
	@Expose
	private String amount;
	@Expose
	private String summary;
	@Expose
	@SerializedName("client_ip")
	private String clientIp;

	public BalanceUnfreezeRequest() {
		super("新浪解冻余额", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("balance_unfreeze");
		this.outUnfreezeNo = IDWorker.INSTANCE.nextSid();
	}

	public String getOutUnfreezeNo() {
		return outUnfreezeNo;
	}

	public void setOutUnfreezeNo(String outUnfreezeNo) {
		this.outUnfreezeNo = outUnfreezeNo;
	}

	public String getOutFreezeNo() {
		return outFreezeNo;
	}

	public void setOutFreezeNo(String outFreezeNo) {
		this.outFreezeNo = outFreezeNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
