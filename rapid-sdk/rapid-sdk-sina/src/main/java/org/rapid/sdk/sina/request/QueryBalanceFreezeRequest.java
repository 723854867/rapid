package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.enums.AccountType;
import org.rapid.sdk.sina.response.QueryBalanceFreezeResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryBalanceFreezeRequest extends UserRequest<QueryBalanceFreezeResponse> {

	
	private static final long serialVersionUID = -6235430163198110144L;
	@Expose
	@SerializedName("out_freeze_no")
	private String outFreezeNo;
	
	@Expose
	@SerializedName("account_type")
	private String accountType;
	
	@Expose
	@SerializedName("amount")
	private String amount;
	
	@Expose
	@SerializedName("summary")
	private String summary;
	
	@Expose
	@SerializedName("client_ip")
	private String clientIp;
	
	
	public QueryBalanceFreezeRequest() {
		super("冻结余额", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("balance_freeze");
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType.name();
	}

	public String getOutFreezeNo() {
		return outFreezeNo;
	}

	public void setOutFreezeNo(String outFreezeNo) {
		this.outFreezeNo = outFreezeNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	
}
