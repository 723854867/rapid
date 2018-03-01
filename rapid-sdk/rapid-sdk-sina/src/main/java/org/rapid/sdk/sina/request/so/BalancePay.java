package org.rapid.sdk.sina.request.so;

import org.rapid.sdk.sina.enums.AccountType;

/**
 * 余额支付
 */
public class BalancePay extends PayMethod {

	private static final long serialVersionUID = 2414624931923421528L;
	
	private AccountType accountType;
	
	public BalancePay() {
		setName("balance");
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return getName() + "^{0}^" + accountType.name();
	}
}
