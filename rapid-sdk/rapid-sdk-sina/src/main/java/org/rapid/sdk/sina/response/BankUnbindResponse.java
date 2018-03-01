package org.rapid.sdk.sina.response;

public class BankUnbindResponse extends SinaResponse {

	private static final long serialVersionUID = -600695869714329266L;

	private String ticket;
	
	public String getTicket() {
		return ticket;
	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}
