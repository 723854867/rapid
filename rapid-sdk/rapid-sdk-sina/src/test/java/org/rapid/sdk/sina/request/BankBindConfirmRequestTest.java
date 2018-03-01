package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.BankBindResponse;

public class BankBindConfirmRequestTest extends SinaTest {

	@Test
	public void test() {
		BankBindConfirmRequest request = new BankBindConfirmRequest();
		request.setTicket("5e4e652fa26a4b9b8466591099e90194");
		request.setValidCode("297027");
		request.setClientIp("127.0.0.1");
		BankBindResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		System.out.println(response.getCardId() + " " + response.getTicket());
//		String cardId = "225140,225141";
	}
}
