package org.rapid.sdk.sina.request;

import java.math.BigDecimal;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.request.so.BindingPay;
import org.rapid.sdk.sina.response.RechargeResponse;

public class RechargeRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		RechargeRequest request = new RechargeRequest();
		BindingPay pay = new BindingPay();
		request.amount(pay, new BigDecimal("1.25"));
		request.setPayerIp("127.0.0.1");
		request.setIdentityId("704965772327");
		RechargeResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
	}
}
