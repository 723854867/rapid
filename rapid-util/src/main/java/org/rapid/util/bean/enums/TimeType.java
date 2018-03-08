package org.rapid.util.bean.enums;

public enum TimeType {

	SECOND(1000),
	MINUTE(60000),
	HOUR(3600000),
	DAY(86400000),
	WEEK(604800000),
	MONTH(2592000000l),
	SEASON(7776000000l),
	YEAR(31104000000l);
	
	private long millis;
	
	private TimeType(long millis) {
		this.millis = millis;
	}
	
	public long millis() {
		return this.millis;
	}
}
