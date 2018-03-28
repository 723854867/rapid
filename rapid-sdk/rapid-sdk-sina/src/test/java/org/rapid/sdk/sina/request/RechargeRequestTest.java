package org.rapid.sdk.sina.request;

import java.math.BigDecimal;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.request.so.OnlineBankPay;
import org.rapid.sdk.sina.response.RechargeResponse;
import org.rapid.util.serialize.SerializeUtil;

public class RechargeRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		RechargeRequest request = new RechargeRequest();
		OnlineBankPay pay = new OnlineBankPay();
		request.amount(pay, new BigDecimal("50000"));
		request.setPayerIp("127.0.0.1");
		request.setIdentityId("420223089893179392");
		RechargeResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		System.out.println(SerializeUtil.GSON.toJson(response));
	}
}
