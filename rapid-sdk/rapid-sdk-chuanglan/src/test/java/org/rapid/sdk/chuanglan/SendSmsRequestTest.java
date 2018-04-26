package org.rapid.sdk.chuanglan;

import org.junit.Test;
import org.rapid.sdk.chuanglan.request.SendSmsRequest;
import org.rapid.sdk.chuanglan.response.SendSmsResponse;

public class SendSmsRequestTest extends ChuangLanTest {

	@Test
	public void testExecuteCaptcha() {
		SendSmsRequest request = new SendSmsRequest();
		request.setMsg("尊敬的微钱进会员,您的短信验证码是123456,请您在5分钟之内验证.");
		request.setMobile("+8615888837752");
		request.execute();
	}
	
	@Test
	public void testExecute() {
		SendSmsRequest request = new SendSmsRequest();
		request.setMsg("尊敬的微钱进用户您好，您在微钱进投资的产品将于{0}到期，请关注查收。");
		request.setMobile("15888837752,13858192747");
		SendSmsResponse response = request.execute();
		System.out.println(response.getMsgId() + " " + response.code());
	}
}
