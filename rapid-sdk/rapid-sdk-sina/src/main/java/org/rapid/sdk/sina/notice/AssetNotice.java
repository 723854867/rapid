package org.rapid.sdk.sina.notice;

import javax.validation.constraints.NotEmpty;

public class AssetNotice extends SinaNotice {

	private static final long serialVersionUID = 7287692695576876636L;

	@NotEmpty
	private String out_bid_no;
	@NotEmpty
	private String inner_bid_no;
	@NotEmpty
	private String bid_status;
	private String reject_reason;

	public String getOut_bid_no() {
		return out_bid_no;
	}

	public void setOut_bid_no(String out_bid_no) {
		this.out_bid_no = out_bid_no;
	}

	public String getInner_bid_no() {
		return inner_bid_no;
	}

	public void setInner_bid_no(String inner_bid_no) {
		this.inner_bid_no = inner_bid_no;
	}

	public String getBid_status() {
		return bid_status;
	}

	public void setBid_status(String bid_status) {
		this.bid_status = bid_status;
	}

	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

}
