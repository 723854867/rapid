package org.rapid.sdk.sina.notice;

import javax.validation.constraints.NotEmpty;

public class CommonNotice extends SinaNotice {

	private static final long serialVersionUID = 834745046749229728L;

	private String notify_request_no;
	@NotEmpty
	private String identity_id;
	private String identity_type;
	private String member_id;
	@NotEmpty
	private String event_result;
	private String event_detail;

	public String getNotify_request_no() {
		return notify_request_no;
	}

	public void setNotify_request_no(String notify_request_no) {
		this.notify_request_no = notify_request_no;
	}

	public String getIdentity_id() {
		return identity_id;
	}

	public void setIdentity_id(String identity_id) {
		this.identity_id = identity_id;
	}

	public String getIdentity_type() {
		return identity_type;
	}

	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getEvent_result() {
		return event_result;
	}

	public void setEvent_result(String event_result) {
		this.event_result = event_result;
	}

	public String getEvent_detail() {
		return event_detail;
	}

	public void setEvent_detail(String event_detail) {
		this.event_detail = event_detail;
	}
}
