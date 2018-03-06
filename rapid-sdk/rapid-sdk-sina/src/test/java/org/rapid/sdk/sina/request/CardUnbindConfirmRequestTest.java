package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.SinaResponse;

public class CardUnbindConfirmRequestTest extends SinaTest {

	@Test
	public void test() {
		CardUnbindConfirmRequest request = new CardUnbindConfirmRequest();
		request.setIdentityId("704965772327");
		request.setTicket("dce8e3cef01943f9be99f283761de042");
		request.setValidCode("122540");
		request.setClientIp("127.0.0.1");
		SinaResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
//		String ticket = "f77c78ce26654c44ab6e3cf552376444";
	}
}
