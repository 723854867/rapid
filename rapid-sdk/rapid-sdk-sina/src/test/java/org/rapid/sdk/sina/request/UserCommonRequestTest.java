package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.enums.UserAction;
import org.rapid.sdk.sina.response.CommonResponse;

public class UserCommonRequestTest extends SinaTest {

	@Test
	public void test() {
		UserCommonRequest request = new UserCommonRequest(UserAction.QUER_PWD);
		request.setIdentityId("704965772327");
		CommonResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc() + " " + response.getRedirectUrl() + " " + response.getIsSetPaypass());
	}
}
