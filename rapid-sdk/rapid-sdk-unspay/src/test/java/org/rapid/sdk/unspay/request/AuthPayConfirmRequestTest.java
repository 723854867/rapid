package org.rapid.sdk.unspay.request;

import org.junit.Test;
import org.rapid.sdk.unspay.UnspayTest;
import org.rapid.sdk.unspay.response.AuthPayConfirmResponse;

public class AuthPayConfirmRequestTest extends UnspayTest {

	@Test
	public void testExecute() {
		AuthPayConfirmRequest request = new AuthPayConfirmRequest("678553D42CB972B0E03E8DD44104C20E", "416971461308186624");
		request.setCustomerId("100");
		request.setVericode("690813");
		AuthPayConfirmResponse response = request.execute();
		System.out.println(response.success());
		System.out.println(response.code());
		System.out.println(response.desc());
		System.out.println(response.getStatus());
	}
}
