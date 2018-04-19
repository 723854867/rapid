package org.rapid.core;

import javax.annotation.Resource;

import org.junit.Test;
import org.rapid.core.http.HttpClient;

import okhttp3.Request;
import okhttp3.Response;

public class HttpClientTest extends CoreTest {

	@Resource
	private HttpClient client;
	
	@Test
	public void testExecute() throws Exception {
		Request request = new Request.Builder().url("https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=6236681420018857854&cardBinCheck=true").build();
		Response response = client.request(request);
		System.out.println(response.body().string());
	}
}
