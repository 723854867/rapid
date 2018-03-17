package org.rapid.sdk.sina.enums;

public enum WithdrawState {

	// 等待钱从中间账户转入到存钱罐
	PAY_WAIT,
	// 确认超时
	PAY_TIMEOUT,
	// 钱转入失败
	PAY_FAIL,
	// 钱转入成功：可以提现
	PAY_SUCCESS,
	INIT,
	SUCCESS,
	FAILED,
	PROCESSING,
	RETURNT_TICKET;
}
