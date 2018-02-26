package org.rapid.sdk.unspay.request;

import org.junit.Test;
import org.rapid.sdk.unspay.UnspayTest;
import org.rapid.sdk.unspay.response.AuthPayQueryResponse;

public class AuthPayQueryRequestTest extends UnspayTest {

	@Test
	public void testExecute() {
		AuthPayQueryRequest request = new AuthPayQueryRequest("416971461308186624");
		AuthPayQueryResponse response = request.execute();
		System.out.println(response.success());
		System.out.println(response.code());
		System.out.println(response.desc());
	}
}
