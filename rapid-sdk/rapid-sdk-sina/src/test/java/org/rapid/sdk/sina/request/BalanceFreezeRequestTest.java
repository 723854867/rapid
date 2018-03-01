package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.SinaResponse;

public class BalanceFreezeRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		BalanceFreezeRequest request = new BalanceFreezeRequest();
		request.setIdentityId("704965772327");
		request.setAmount("1");
		request.setSummary("test");
		request.setClientIp("127.0.0.1");
		SinaResponse response = request.execute();
		System.out.println(request.getOutFreezeNo());
		System.out.println(response.code() + " " + response.desc());
	}
}
