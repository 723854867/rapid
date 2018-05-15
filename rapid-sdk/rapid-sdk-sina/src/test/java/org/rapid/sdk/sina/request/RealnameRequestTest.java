package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.SinaResponse;

public class RealnameRequestTest extends SinaTest {

	@Test
	public void test() {
		RealnameRequest request = new RealnameRequest();
		request.setIdentityId("445948416376176640");
		request.setRealName("帐篷");
		request.setCertNo("33012719870603341X");
		request.setClientIp("127.0.0.1");
		SinaResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
	}
}
