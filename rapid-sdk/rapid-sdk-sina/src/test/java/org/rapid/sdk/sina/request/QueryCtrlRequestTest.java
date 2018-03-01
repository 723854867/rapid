package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.QueryCtrlResponse;

public class QueryCtrlRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		QueryCtrlRequest request = new QueryCtrlRequest();
		request.setOutCtrlNo("418431151728754688");
		QueryCtrlResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		System.out.println(response.getCtrlStatus() + " " + response.getErrorMsg());
	}
}
