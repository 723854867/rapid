package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.CardBindResponse;

/**
 * 卡号不是本人也可以
 */
public class CardBindRequestTest extends SinaTest {

	@Test
	public void test() {
		CardBindRequest request = new CardBindRequest();
		request.setBankCode("ICBC");
		request.setBankAccountNo("6212261202025453326");
		request.setIdentityId("704965772327");
		request.setClientIp("127.0.0.1");
		request.setPhoneNo("15888837752");
		CardBindResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		System.out.println(response.getCardId() + " " + response.getTicket());
	}
}
