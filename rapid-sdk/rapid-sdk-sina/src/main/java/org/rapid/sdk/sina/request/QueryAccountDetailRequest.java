package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.QueryAccountDetailResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 提供最多90日内用户账户收支明细，包括交易、充值、提现中涉及的所有账户余额变动记录。
 * 
 * @author lynn
 */
public class QueryAccountDetailRequest extends UserRequest<QueryAccountDetailResponse> {

	private static final long serialVersionUID = 4788072585421994880L;

	@Expose
	@SerializedName("account_type")
	private String accountType;
	// yyyyMMddHHmmss
	@Expose
	@SerializedName("start_time")
	private String startTime;
	// yyyyMMddHHmmss
	@Expose
	@SerializedName("end_time")
	private String endTime;
	// 从1开始，默认为1
	@Expose
	@SerializedName("page_no")
	private String pageNo;
	// 不超过30，默认20
	@Expose
	@SerializedName("page_size")
	private String pageSize;

	public QueryAccountDetailRequest() {
		super("新浪查询收支明细", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("query_account_details");
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
