package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.CardUnbindResponse;

public class CardUnbindRequestTest extends SinaTest {

	@Test
	public void test() {
		CardUnbindRequest request = new CardUnbindRequest();
		request.setIdentityId("704965772327");
		request.setClientIp("127.0.0.1");
		request.setCardId("225129");
		CardUnbindResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		System.out.println(response.getTicket());
//		String ticket = "dce8e3cef01943f9be99f283761de042";
	}
}
