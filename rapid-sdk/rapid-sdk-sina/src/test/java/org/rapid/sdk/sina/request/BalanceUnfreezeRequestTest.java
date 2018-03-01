package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.SinaResponse;

public class BalanceUnfreezeRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		BalanceUnfreezeRequest request = new BalanceUnfreezeRequest();
		request.setIdentityId("704965772327");
		request.setSummary("test");
		request.setClientIp("127.0.0.1");
		request.setOutFreezeNo("418429215147294720");
		SinaResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
	}
}
