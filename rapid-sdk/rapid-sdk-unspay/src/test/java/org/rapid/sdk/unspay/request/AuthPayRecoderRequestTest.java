package org.rapid.sdk.unspay.request;

import org.junit.Test;
import org.rapid.sdk.unspay.UnspayTest;
import org.rapid.sdk.unspay.response.AuthPayRecodeResponse;

public class AuthPayRecoderRequestTest extends UnspayTest {

	@Test
	public void testExecute() {
		AuthPayReCodeRequest request = new AuthPayReCodeRequest("678553D42CB972B0E03E8DD44104C20E", "416971461308186624");
		request.setCustomerId("100");
		request.setPhoneNo("15888837752");
		AuthPayRecodeResponse response = request.execute();
		System.out.println(response.code());
		System.out.println(response.desc());
		System.out.println(response.success());
	}
}
