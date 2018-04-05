package org.rapid.sdk.sina.enums;

public enum TradeState {

	// 等待付款
	WAIT_PAY,
	// 已付款
	PAY_FINISHED,
	// 交易失败
	TRADE_FAILED,
	// 交易结束
	TRADE_FINISHED,
	// 交易关闭
	TRADE_CLOSED,
	// 代收冻结成功
	PRE_AUTH_APPLY_SUCCESS,
	// 代收撤销成功
	PRE_AUTH_CANCELED,
	// 提现失败回收中
	WITHDRAW_REBACKING,
	// 提现失败已收回
	WITHDRAW_REBACK;
}
