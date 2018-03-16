package org.rapid.sdk.sina.request;

import java.util.List;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.enums.TradeCode;
import org.rapid.sdk.sina.response.QueryMiddleAccountResponse;
import org.rapid.sdk.sina.response.tips.AccountMiddleTips;

public class QueryMiddleAccountTest extends SinaTest {

	@Test
	public void testExecute() {
		QueryMiddleAccountRequest request = new QueryMiddleAccountRequest();
		request.setOutTradeCode(TradeCode.COLLECT_INVEST);
		QueryMiddleAccountResponse response = request.execute();
		List<AccountMiddleTips> list = response.getList();
		for (AccountMiddleTips tips : list) {
			System.out.println(tips.getTradeCode() + " " + tips.getAccount() + " " + tips.getAmount());
		}
	}
}
