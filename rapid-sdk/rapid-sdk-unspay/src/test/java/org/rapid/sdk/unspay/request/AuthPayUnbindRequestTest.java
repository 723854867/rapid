package org.rapid.sdk.unspay.request;

import org.junit.Test;
import org.rapid.sdk.unspay.UnspayTest;
import org.rapid.sdk.unspay.response.AuthPayUnbindResponse;

public class AuthPayUnbindRequestTest extends UnspayTest {

	@Test
	public void testExecute() {
		AuthPayUnbindRequest request = new AuthPayUnbindRequest("678553D42CB972B0E03E8DD44104C20E", "100");
		AuthPayUnbindResponse response = request.execute();
		System.out.println(response.success());
		System.out.println(response.code());
		System.out.println(response.desc());
	}
}
