package org.rapid.sdk.sina.response;

import org.rapid.sdk.sina.enums.PayState;

import com.google.gson.annotations.SerializedName;

public class QueryCtrlResponse extends SinaResponse {

	private static final long serialVersionUID = 4336690267617042910L;

	@SerializedName("out_ctrl_no")
	private String outCtrlNo;
	// 订单状态
	@SerializedName("ctrl_status")
	private String ctrlStatus;
	// 冻结解冻失败原因
	@SerializedName("error_msg")
	private String errorMsg;

	public String getOutCtrlNo() {
		return outCtrlNo;
	}

	public void setOutCtrlNo(String outCtrlNo) {
		this.outCtrlNo = outCtrlNo;
	}

	public String getCtrlStatus() {
		return ctrlStatus;
	}

	public void setCtrlStatus(String ctrlStatus) {
		this.ctrlStatus = ctrlStatus;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public boolean success() {
		return PayState.valueOf(ctrlStatus) == PayState.SUCCESS;
	}
}
