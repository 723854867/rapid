package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.QueryCardResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryCardRequest extends UserRequest<QueryCardResponse> {

	private static final long serialVersionUID = -2975045274742295532L;
	
	// 如果不传则表示获取该用户所有的绑定银行卡
	@Expose
	@SerializedName("card_id")
	private String cardId;

	public QueryCardRequest() {
		super("新浪查询银行卡", SinaConfig.getGateWayMember());
		setService("query_bank_card");
	}

	public String getCardId() {
		return cardId;
	}
	
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
