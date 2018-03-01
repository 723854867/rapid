package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.enums.AccountType;
import org.rapid.sdk.sina.response.QueryBalanceResponse;
import org.rapid.sdk.sina.response.tips.ProfitTips;

public class QueryBalanceRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		QueryBalanceRequest request = new QueryBalanceRequest();
		request.setIdentityId("200004595271");
		request.setIdentityType("MEMBER_ID");
		request.setAccountType(AccountType.BANK);
		QueryBalanceResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		ProfitTips profit = response.profit();
		System.out.println(profit.getDayProfit() + " " + profit.getMonthProfit() + " " + profit.getTotalProfit());
	}
}
