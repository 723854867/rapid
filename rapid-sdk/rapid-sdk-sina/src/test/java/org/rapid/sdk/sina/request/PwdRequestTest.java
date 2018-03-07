package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.enums.UserAction;
import org.rapid.sdk.sina.response.CommonResponse;

public class PwdRequestTest extends SinaTest {

	@Test
	public void testSet() {
		PwdRequest request = new PwdRequest(UserAction.FIND_PWD);
		request.setIdentityId("704965772327");
		CommonResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc() + " " + response.getRedirectUrl());
	}
}
