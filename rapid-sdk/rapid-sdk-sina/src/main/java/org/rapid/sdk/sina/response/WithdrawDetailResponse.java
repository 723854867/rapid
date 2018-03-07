package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class WithdrawDetailResponse extends SinaResponse {

	private static final long serialVersionUID = -1914541330119233010L;

	@SerializedName("withdraw_list")
	private String withdrawList;

	public String getWithdrawList() {
		return withdrawList;
	}

	public void setWithdrawList(String withdrawList) {
		this.withdrawList = withdrawList;
	}

}
