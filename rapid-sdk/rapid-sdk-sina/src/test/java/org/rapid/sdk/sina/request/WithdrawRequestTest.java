package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.core.IDWorker;
import org.rapid.core.serialize.SerializeUtil;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.WithdrawResponse;

public class WithdrawRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		WithdrawRequest request = new WithdrawRequest();
		request.setOutTradeNo(IDWorker.INSTANCE.nextSid());
		request.setIdentityId("420223089893179392");
		request.setAmount("1");
		request.setUserFee("0");
		request.setUserIp("127.0.0.1");
		request.setCardId("225717");
		request.setWithdrawCloseTime("1m");
		WithdrawResponse response = request.execute();
		System.out.println(response.getWithdrawStatus());
		System.out.println(SerializeUtil.GSON.toJson(response));
	}
}
