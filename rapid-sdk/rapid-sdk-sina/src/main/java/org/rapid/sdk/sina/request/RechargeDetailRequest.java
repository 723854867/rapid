package org.rapid.sdk.sina.request;

import org.rapid.core.IDWorker;
import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.RechargeDetailResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeDetailRequest extends UserRequest<RechargeDetailResponse> {

	private static final long serialVersionUID = -5566897669754307036L;

	@Expose
	@SerializedName("out_trade_no")
	private String outTradeNo;

	public RechargeDetailRequest() {
		super("新浪托管充值查询", SinaConfig.getGateWayOrder());
		setService("query_hosting_deposit");
		this.outTradeNo = IDWorker.INSTANCE.nextSid();
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

}
