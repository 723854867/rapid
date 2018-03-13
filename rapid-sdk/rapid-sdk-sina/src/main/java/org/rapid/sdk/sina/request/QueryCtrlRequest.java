package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.QueryCtrlResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryCtrlRequest extends SinaRequest<QueryCtrlResponse> {

	private static final long serialVersionUID = 4918111288530399238L;

	@Expose
	@SerializedName("out_ctrl_no")
	private String outCtrlNo;
	@Expose
	@SerializedName("extend_param")
	private String extendParam;

	public QueryCtrlRequest() {
		super("查询冻结解冻结果", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("query_ctrl_result");
	}

	public String getOutCtrlNo() {
		return outCtrlNo;
	}

	public void setOutCtrlNo(String outCtrlNo) {
		this.outCtrlNo = outCtrlNo;
	}

	public String getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}
}
