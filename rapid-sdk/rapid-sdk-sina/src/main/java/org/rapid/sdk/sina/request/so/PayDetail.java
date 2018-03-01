package org.rapid.sdk.sina.request.so;

import java.io.Serializable;

/**
 * 代付分账详情
 */
public class PayDetail implements Serializable {

	private static final long serialVersionUID = -4435481890855153663L;

	// 付款人ID
	private String payerId;
	// 付款人类型
	private String payerType = "UID";
	// 付款人账户类型
	private String payerAccountType = "BASIC";
	// 收款人ID
	private String collectId;
	// 收款人类型
	private String collectType = "UID";
	// 收款人账户类型
	private String collectAccountType = "BASIC";
	// 金额
	private String amount;
	// 备注
	private String memo;

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPayerType() {
		return payerType;
	}

	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}

	public String getPayerAccountType() {
		return payerAccountType;
	}

	public void setPayerAccountType(String payerAccountType) {
		this.payerAccountType = payerAccountType;
	}

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	public String getCollectAccountType() {
		return collectAccountType;
	}

	public void setCollectAccountType(String collectAccountType) {
		this.collectAccountType = collectAccountType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
