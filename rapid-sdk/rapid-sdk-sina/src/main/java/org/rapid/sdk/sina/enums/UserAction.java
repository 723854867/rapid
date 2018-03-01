package org.rapid.sdk.sina.enums;

public enum UserAction {

	// 修改密码
	MODIFY_PWD("新浪修改支付密码重定向", "modify_pay_password"),
	
	// 找回密码
	FIND_PWD("新浪找回支付密码重定向", "find_pay_password"),
	
	// 查询是否设置支付密码
	QUER_PWD("新浪查询是否设置支付密码", "query_is_set_pay_password");
	
	private String prefix;
	private String service;
	
	private UserAction(String prefix, String service) {
		this.prefix = prefix;
		this.service = service;
	}
	
	public String prefix() {
		return prefix;
	}
	
	public String service() {
		return service;
	}
}
