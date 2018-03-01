package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.BankUnbindResponse;

public class BankUnbindRequestTest extends SinaTest {

	@Test
	public void test() {
		BankUnbindRequest request = new BankUnbindRequest();
		request.setIdentityId("704965772327");
		request.setClientIp("127.0.0.1");
		request.setCardId("225129");
		BankUnbindResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		System.out.println(response.getTicket());
//		String ticket = "dce8e3cef01943f9be99f283761de042";
	}
}
