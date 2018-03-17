package org.rapid.sdk.sina.enums;

public enum AccountType {

	// 基本户
	BASIC(1),
	// 存钱罐
	SAVING_POT(2),
	// 准备金账户
	RESERVE(3),
	// 保证金户
	ENSURE(4),
	// 银行账户
	BANK(5),
	// 中间账户：投资还款专用
	TRANSFER(6);
	
	private int mark;
	
	private AccountType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final AccountType match(int mark) {
		for (AccountType temp : AccountType.values()) {
			if (temp.mark == mark)
				return temp;
		}
		return null;
	}
}
