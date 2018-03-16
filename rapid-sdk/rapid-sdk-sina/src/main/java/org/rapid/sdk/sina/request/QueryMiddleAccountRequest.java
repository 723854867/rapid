package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.enums.TradeCode;
import org.rapid.sdk.sina.response.QueryMiddleAccountResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryMiddleAccountRequest extends SinaRequest<QueryMiddleAccountResponse> {

	private static final long serialVersionUID = -6364691041741979382L;
	
	@Expose
	@SerializedName("out_trade_code")
	private String outTradeCode;
	
	public String getOutTradeCode() {
		return outTradeCode;
	}
	
	public void setOutTradeCode(TradeCode code) {
		this.outTradeCode = code.mark();
	}

	public QueryMiddleAccountRequest() {
		super("新浪查询中间账户", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("query_middle_account");
	}
}
