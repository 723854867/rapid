package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.SinaResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankUnbindConfirmRequest extends UserRequest<SinaResponse> {

	private static final long serialVersionUID = 8155848489612705416L;

	@Expose
	private String ticket;
	@Expose
	@SerializedName("valid_code")
	private String validCode;
	@Expose
	@SerializedName("client_ip")
	private String clientIp;

	public BankUnbindConfirmRequest() {
		super("新浪解绑银行卡推进", SinaConfig.getGateWayMember());
		setService("unbinding_bank_card_advance");
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
