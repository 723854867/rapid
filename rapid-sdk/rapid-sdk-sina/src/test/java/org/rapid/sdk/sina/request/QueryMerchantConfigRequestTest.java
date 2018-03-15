package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.QueryMerchantConfigResponse;

public class QueryMerchantConfigRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		QueryMerchantConfigRequest request = new QueryMerchantConfigRequest();
		QueryMerchantConfigResponse response = request.execute();
		System.out.println(response.getConfigValue());
	}
}
