package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CardBindResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 确认绑定银行卡
 */
public class CardBindConfirmRequest extends SinaRequest<CardBindResponse> {

	private static final long serialVersionUID = -2281544212572534030L;

	@Expose
	private String ticket;
	@Expose
	@SerializedName("valid_code")
	private String validCode;
	@Expose
	@SerializedName("client_ip")
	private String clientIp;
	@Expose
	@SerializedName("extend_param")
	private String extendParam;

	public CardBindConfirmRequest() {
		super("新浪绑定银行卡推进", SinaConfig.getGateWayMember());
		setService("binding_bank_card_advance");
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
	
	public String getExtendParam() {
		return extendParam;
	}
	
	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}
}
