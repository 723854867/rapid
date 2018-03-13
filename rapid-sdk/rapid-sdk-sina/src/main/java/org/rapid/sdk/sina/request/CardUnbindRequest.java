package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CardUnbindResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardUnbindRequest extends UserRequest<CardUnbindResponse> {

	private static final long serialVersionUID = 206933137089000118L;

	@Expose
	@SerializedName("card_id")
	private String cardId;
	@Expose
	@SerializedName("advance_flag")
	private String advanceFlag = "Y";
	@Expose
	@SerializedName("client_ip")
	private String clientIp;

	public CardUnbindRequest() {
		super("新浪解绑银行卡", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("unbinding_bank_card");
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getAdvanceFlag() {
		return advanceFlag;
	}

	public void setAdvanceFlag(String advanceFlag) {
		this.advanceFlag = advanceFlag;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
