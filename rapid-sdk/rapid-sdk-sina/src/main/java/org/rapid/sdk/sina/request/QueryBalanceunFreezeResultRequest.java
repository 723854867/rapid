package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.QueryBalanceFreezeResultResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryBalanceunFreezeResultRequest extends UserRequest<QueryBalanceFreezeResultResponse> {

	private static final long serialVersionUID = -9122972717894438655L;
	@Expose
	@SerializedName("out_ctrl_no")
	private String outCtrlNo;

	public QueryBalanceunFreezeResultRequest() {
		super("查询冻结解冻结果", SinaConfig.getGateWayMember());
		setService("query_ctrl_result");
	}

	public String getOutCtrlNo() {
		return outCtrlNo;
	}

	public void setOutCtrlNo(String outCtrlNo) {
		this.outCtrlNo = outCtrlNo;
	}

}
