package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.CreateSingleHostingPayTradeResponse;

public class CreateSingleHostingPayTradeRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		CreateSingleHostingPayTradeRequest request = new CreateSingleHostingPayTradeRequest();
		request.setPayeeIdentityId("423854531235807232");
		request.setAmount("20");
		request.setSummary("充值代付");
		request.setUserIp("127.0.0.1");
		CreateSingleHostingPayTradeResponse response = request.execute();
		System.out.println(response.getOutTradeNo() + " " + response.getTradeStatus());
	}
}
