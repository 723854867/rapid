package org.rapid.sdk.sina.response.tips;

import java.io.Serializable;
import java.math.BigDecimal;

import org.rapid.sdk.sina.enums.TradeCode;

public class AccountMiddleTips implements Serializable {

	private static final long serialVersionUID = -5017993851773995977L;

	private String account;
	private BigDecimal amount;
	private TradeCode tradeCode;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TradeCode getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(TradeCode tradeCode) {
		this.tradeCode = tradeCode;
	}
}
