package org.rapid.sdk.sina.enums;

public enum AccountDetailField {

	PURPOSE(1, "purpose"),
	TIME(2, "time"),
	INCOME(3, "income") {
		@Override
		public String process(String value) {
			return value.equals("+") ? "true" : "false";
		}
	},
	QUPTA(4, "quota"),
	FINAL_QUOTA(5, "finalQuota"),
	BIZ(6, "biz");
	
	private String mark;
	private int priority;
	
	private AccountDetailField(int priority, String mark) {
		this.mark = mark;
		this.priority = priority;
	}
	
	public int priority() {
		return priority;
	}
	
	public String mark() {
		return mark;
	}
	
	public String process(String value) {
		return value;
	}
}
