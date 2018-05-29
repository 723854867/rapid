package org.rapid.sdk.sina.request;

import java.math.BigDecimal;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.request.so.BindingCardPay;
import org.rapid.sdk.sina.response.CreateSingleHostingPayToCardTradeResponse;
import org.rapid.sdk.sina.response.CreateSingleHostingPayTradeResponse;
import org.rapid.util.serialize.SerializeUtil;

public class CreateSingleHostingPayTradeRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		CreateSingleHostingPayTradeRequest request = new CreateSingleHostingPayTradeRequest();
		request.setPayeeIdentityId("423854531235807232");
		request.setAmount("20");
		request.setSummary("充值代付");
		request.setUserIp("127.0.0.1");
		CreateSingleHostingPayTradeResponse response = request.execute();
		System.out.println(response.getOutTradeNo() + " " + response.getTradeStatus());
	}
	
	@Test
	public void testPayToCard() { 
		CreateSingleHostingPayToCardTradeRequest request = new CreateSingleHostingPayToCardTradeRequest();
		request.setOutTradeNo(org.rapid.core.IDWorker.INSTANCE.nextSid());
		BindingCardPay pay = new BindingCardPay();
		pay.setCardId("235287");
		pay.setSinaId("447061371406778368");
		request.setCollectMethod(pay.toString());
		request.setAmount(BigDecimal.valueOf(50).toString());
		request.setSummary("放款");
		request.setGoodsId("");
//		builder.setNotifyUrl(configService.get(WqjConsts.GlobalKeys.URL_NOTICE_LOANOUT_SINA));
		request.setUserIp("127.0.0.1");
		System.out.println(SerializeUtil.GSON.toJson(request));
		CreateSingleHostingPayToCardTradeResponse response = request.execute();
		System.out.println(SerializeUtil.GSON.toJson(response));
	}
}
