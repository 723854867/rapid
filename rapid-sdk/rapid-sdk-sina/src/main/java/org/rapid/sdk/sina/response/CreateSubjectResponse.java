package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class CreateSubjectResponse extends SinaResponse {

	private static final long serialVersionUID = 7055659851547134875L;

	@SerializedName("out_bid_no")
	private String outBidNo;
	@SerializedName("inner_bid_no")
	private String innerBidNo;
	@SerializedName("bid_status")
	private String bidStatus;
	@SerializedName("reject_reason")
	private String rejectReason;
	@SerializedName("gmt_create")
	private String gmtCreate;
	@SerializedName("gmt_modify")
	private String gmtModify;

	public String getOutBidNo() {
		return outBidNo;
	}

	public void setOutBidNo(String outBidNo) {
		this.outBidNo = outBidNo;
	}

	public String getInnerBidNo() {
		return innerBidNo;
	}

	public void setInnerBidNo(String innerBidNo) {
		this.innerBidNo = innerBidNo;
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(String gmtModify) {
		this.gmtModify = gmtModify;
	}
}
