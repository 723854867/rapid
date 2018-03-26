package org.rapid.sdk.sina.request.so;

import java.text.MessageFormat;

import org.rapid.sdk.sina.enums.IdentityType;

public class BindingCardPay extends PayMethod {

	private static final long serialVersionUID = 6043616684054998585L;
	
	private static final String FORMAT = "{0}^{1},{2},{3}";
	
	private String sinaId;
	private String cardId;
	private IdentityType identityType = IdentityType.UID;
	
	public BindingCardPay() {
		setName("binding_card");
	}

	public String getSinaId() {
		return sinaId;
	}
	
	public void setSinaId(String sinaId) {
		this.sinaId = sinaId;
	}
	
	public String getCardId() {
		return cardId;
	}
	
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public IdentityType getIdentityType() {
		return identityType;
	}
	
	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format(FORMAT, getName(), sinaId, identityType.name(), cardId);
	}
}
