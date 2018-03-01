package org.rapid.sdk.sina.response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.rapid.sdk.sina.enums.ProfitField;
import org.rapid.sdk.sina.response.tips.ProfitTips;
import org.rapid.util.reflect.BeanUtil;

import com.google.gson.annotations.SerializedName;

public class QueryBalanceResponse extends SinaResponse {

	private static final long serialVersionUID = -166206677507298989L;
	
	private static ProfitField[] fields = ProfitField.values();
	
	static {
		Arrays.sort(fields, (o1, o2) -> o1.priority() - o2.priority());
	}

	// 余额/基金份额
	private String balance;
	// 可用余额/基金份额
	@SerializedName("available_balance")
	private String availableBalance;
	// 存钱罐收益：昨日收益^最近一月收益^总收益
	private String bonus;

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	
	public ProfitTips profit() {
		if (null == bonus)
			return new ProfitTips();
		int idx = 0;
		StringTokenizer tokenizer = new StringTokenizer(bonus, "^");
		Map<String, String> params = new HashMap<String, String>();
		while (tokenizer.hasMoreElements()) {
			String value = tokenizer.nextToken();
			ProfitField field = fields[idx++];
			params.put(field.mark(), value);
		}
		ProfitTips bean = new ProfitTips();
		return BeanUtil.mapToBean(params, bean);
	}
}
