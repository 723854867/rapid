package org.rapid.sdk.sina.response.tips;

import java.io.Serializable;

public class AccountDetailTips implements Serializable {

	private static final long serialVersionUID = 2446048839462544226L;

	// 明细摘要
	private String purpose;
	// 发生时间
	private String time;
	// 是否入账
	private boolean income;
	// 发生额
	private String quota;
	// 交易后余额
	private String finalQuota;
	// 业务类型
	private String biz;

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isIncome() {
		return income;
	}

	public void setIncome(boolean income) {
		this.income = income;
	}

	public String getQuota() {
		return quota;
	}
	
	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getFinalQuota() {
		return finalQuota;
	}

	public void setFinalQuota(String finalQuota) {
		this.finalQuota = finalQuota;
	}

	public String getBiz() {
		return biz;
	}

	public void setBiz(String biz) {
		this.biz = biz;
	}
}
