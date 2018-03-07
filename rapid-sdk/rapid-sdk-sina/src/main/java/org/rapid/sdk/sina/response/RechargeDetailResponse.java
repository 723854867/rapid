package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class RechargeDetailResponse extends SinaResponse {

	private static final long serialVersionUID = -1914541330119233010L;

	@SerializedName("deposit_list")
	private String depositList;

	public String getDepositList() {
		return depositList;
	}

	public void setDepositList(String depositList) {
		this.depositList = depositList;
	}

}
