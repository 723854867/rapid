package org.rapid.sdk.sina.enums;

public enum TradeCode {

	COLLECT_OTHER("1000"),
	COLLECT_INVEST("1001"),
	COLLECT_REPAY("1002"),
	PAY_OTHER("2000"),
	PAY_LOAN("2001"),
	PAY_PRFIT("2002");
	
	private String mark;
	
	private TradeCode(String mark) {
		this.mark = mark;
	}
	
	public String mark() {
		return mark;
	}
}
