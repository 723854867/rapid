package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class QueryBalanceFreezeResultResponse extends SinaResponse {

	private static final long serialVersionUID = 7282555018904782507L;

	// 可用余额/基金份额
	@SerializedName("out_ctrl_no")
	private String outCtrlNo;

	// 冻结解冻订单状态  SUCCESS	成功(系统会异步通知)   FAILED	失败(系统会异步通知)      PROCESSING	处理中(系统不会异步通知)
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

}
