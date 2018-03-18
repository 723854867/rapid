package org.rapid.sdk.sina.request;

import org.junit.Test;
import org.rapid.core.serialize.SerializeUtil;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.WithdrawResponse;

public class WithdrawRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		WithdrawRequest request = new WithdrawRequest();
		request.setOutTradeNo("424933861630148608");
		request.setIdentityId("423854531235807232");
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
