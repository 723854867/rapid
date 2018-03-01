package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.SinaResponse;

public class RegisterRequestTest extends SinaTest {

	@Test
	public void testExecute() { 
		RegisterRequest request = new RegisterRequest();
		String identityId = "704965772327";
		System.out.println(identityId);
		request.setIdentityId(identityId);
		request.setClientIp("127.0.0.1");
		SinaResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
	}
}
