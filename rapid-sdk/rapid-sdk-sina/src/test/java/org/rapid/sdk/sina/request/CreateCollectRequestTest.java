package org.rapid.sdk.sina.request;

import java.math.BigDecimal;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.enums.TradeCode;
import org.rapid.sdk.sina.response.CreateCollectResponse;

public class CreateCollectRequestTest extends SinaTest {

	@Test
	public void testExecute() { 
		CreateCollectRequest request = new CreateCollectRequest();
		request.setAmount(new BigDecimal("1.23"));
		request.setOutTradeCode(TradeCode.COLLECT_OTHER);
		request.setSummary("充值");
		request.setPayerId("704965772327");
		request.setPayerIp("127.0.0.1");
		CreateCollectResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
	}
}
