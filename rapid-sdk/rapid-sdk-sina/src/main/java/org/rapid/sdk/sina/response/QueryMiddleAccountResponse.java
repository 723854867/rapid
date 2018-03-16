package org.rapid.sdk.sina.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.rapid.sdk.sina.enums.TradeCode;
import org.rapid.sdk.sina.response.tips.AccountMiddleTips;
import org.rapid.util.StringUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryMiddleAccountResponse extends SinaResponse {

	private static final long serialVersionUID = 1623623280893914735L;

	@Expose
	@SerializedName("account_list")
	private String accountList;
	
	public String getAccountList() {
		return accountList;
	}
	
	public void setAccountList(String accountList) {
		this.accountList = accountList;
	}
	
	public List<AccountMiddleTips> getList() {
		if (!StringUtil.hasText(accountList))
			return new ArrayList<AccountMiddleTips>();
		List<AccountMiddleTips> list = new ArrayList<AccountMiddleTips>();
		StringTokenizer tokenizer = new StringTokenizer(this.accountList, "|");
		while (tokenizer.hasMoreElements()) {
			String temp = tokenizer.nextToken();
			list.add(_parse(temp));
		}
		return list;
	}
	
	private AccountMiddleTips _parse(String info) {
		StringTokenizer tokenizer = new StringTokenizer(this.accountList, "^");
		int idx = 0;
		AccountMiddleTips tips = new AccountMiddleTips();
		while (tokenizer.hasMoreElements()) {
			String temp = tokenizer.nextToken();
			if (idx == 0) 
				tips.setTradeCode(TradeCode.match(temp));
			else if (idx == 1) 
				tips.setAccount(temp);
			else if (idx == 2)
				tips.setAmount(new BigDecimal(temp));
			else 
				throw new RuntimeException("新浪中间账户数据解析错误:" + info);
			idx++;
		}
		return tips;
	}
}
