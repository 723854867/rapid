package org.rapid.sdk.sina.request;

import java.math.BigDecimal;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.enums.AccountType;
import org.rapid.sdk.sina.enums.TradeCode;
import org.rapid.sdk.sina.request.so.BalancePay;
import org.rapid.sdk.sina.response.CreateHostingCollectResponse;

public class CreateHostingCollectRequestTest extends SinaTest {

	@Test
	public void testExecute() { 
		CreateHostingCollectRequest request = new CreateHostingCollectRequest();
		request.setOutTradeCode(TradeCode.COLLECT_INVEST);
		request.setSummary("充值待收");
		BalancePay pay = new BalancePay();
		request.setOutTradeNo("424516643503210496");
		pay.setAccountType(AccountType.SAVING_POT);
		request.setAmount(new BigDecimal("1"), pay);
		request.setPayerId("423854531235807232");
		request.setPayerIp("127.0.0.1");
		CreateHostingCollectResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
	}
}
