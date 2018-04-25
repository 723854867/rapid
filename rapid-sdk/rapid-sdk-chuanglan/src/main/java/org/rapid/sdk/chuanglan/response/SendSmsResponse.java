package org.rapid.sdk.chuanglan.response;

import org.rapid.core.http.HttpResponse;

public class SendSmsResponse implements HttpResponse {

	private static final long serialVersionUID = -3621982202850684731L;
	
	private String code;
	private String msgId;
	private String time;

	@Override
	public String code() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String desc() {
		return code;
	}

	@Override
	public boolean success() {
		return this.code.equals("0");
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}
