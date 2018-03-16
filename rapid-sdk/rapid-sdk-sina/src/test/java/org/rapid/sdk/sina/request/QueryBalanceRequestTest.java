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
		request.setIdentityId("423854531235807232");
		request.setAccountType(AccountType.BASIC);
		QueryBalanceResponse response = request.execute();
		ProfitTips profit = response.profit();
		System.out.println(response.getBalance() + " " + response.getAvailableBalance());
		System.out.println(profit.getDayProfit() + " " + profit.getMonthProfit() + " " + profit.getTotalProfit());
	}
}
