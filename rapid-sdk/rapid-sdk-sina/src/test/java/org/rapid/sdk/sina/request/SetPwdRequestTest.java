package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.CommonResponse;

public class SetPwdRequestTest extends SinaTest {

	@Test
	public void test() {
		SetPwdRequest request = new SetPwdRequest();
		request.setIdentityId("704965772327");
		CommonResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc() + " " + response.getRedirectUrl());
	}
}
