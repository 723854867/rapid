package org.rapid.sdk.sina.notice;

public class CompanyNotice extends SinaNotice {

	private static final long serialVersionUID = -3227242006888724873L;

	private String audit_order_no;
	private String inner_order_no;
	private String audit_status;
	private String audit_message;

	public String getAudit_order_no() {
		return audit_order_no;
	}

	public void setAudit_order_no(String audit_order_no) {
		this.audit_order_no = audit_order_no;
	}

	public String getInner_order_no() {
		return inner_order_no;
	}

	public void setInner_order_no(String inner_order_no) {
		this.inner_order_no = inner_order_no;
	}

	public String getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}

	public String getAudit_message() {
		return audit_message;
	}

	public void setAudit_message(String audit_message) {
		this.audit_message = audit_message;
	}

}
