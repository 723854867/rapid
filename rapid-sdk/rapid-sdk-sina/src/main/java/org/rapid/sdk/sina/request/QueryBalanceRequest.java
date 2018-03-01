package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.enums.AccountType;
import org.rapid.sdk.sina.response.QueryBalanceResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryBalanceRequest extends UserRequest<QueryBalanceResponse> {

	private static final long serialVersionUID = -231587883882188648L;
	
	@Expose
	@SerializedName("account_type")
	private String accountType;

	public QueryBalanceRequest() {
		super("新浪查询余额/基金份额", SinaConfig.getGateWayMember());
		setService("query_balance");
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType.name();
	}
}
