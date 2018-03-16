package org.rapid.sdk.sina.enums.sina;

public enum OrderType {

	RECHARGE_PAY(1),
	RECHARGE_COLLECT(2);
	
	private int mark;
	
	private OrderType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final OrderType match(int type) {
		for (OrderType temp : OrderType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
