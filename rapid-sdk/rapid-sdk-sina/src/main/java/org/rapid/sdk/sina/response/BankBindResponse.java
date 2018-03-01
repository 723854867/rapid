package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class BankBindResponse extends SinaResponse {

	private static final long serialVersionUID = -7573843293495092804L;

	@SerializedName("card_id")
	private String cardId;
	@SerializedName("is_verified")
	private String isVerified;
	private String ticket;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}
