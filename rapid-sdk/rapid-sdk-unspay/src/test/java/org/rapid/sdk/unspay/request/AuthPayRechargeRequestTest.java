package org.rapid.sdk.unspay.request;

import org.junit.Test;
import org.rapid.sdk.unspay.UnspayTest;
import org.rapid.sdk.unspay.response.AuthPayRechargeResponse;

public class AuthPayRechargeRequestTest extends UnspayTest {

	// 首次充值
	@Test
	public void testFirst() {
		AuthPayRechargeRequest request = new AuthPayRechargeRequest();
		request.setCustomerId("100");
		request.setName("张辛林");
		request.setPhoneNo("15888837752");
		request.setCardNo("6212261202025453326");
		request.setIdCardNo("33012719870603341X");
		request.setPurpose("test");
		request.setAmount("0.01");
		request.setResponseUrl("http://www.baidu.com");
		AuthPayRechargeResponse response = request.execute();
		System.out.println(response.success());
		System.out.println(response.code());
		System.out.println(response.desc());
		System.out.println(response.getToken());
	}
	
	// 再次充值
	@Test
	public void testSecond() {
		String token = "32282C935E3D6FE91069775263B4318E";
		AuthPayRechargeRequest request = new AuthPayRechargeRequest();
		request.setCustomerId("100");
		request.setToken(token);
		request.setPurpose("test");
		request.setAmount("0.01");
		request.setResponseUrl("http://www.baidu.com");
		AuthPayRechargeResponse response = request.execute();
		System.out.println(response.success());
		System.out.println(response.code());
		System.out.println(response.desc());
		System.out.println(response.getToken());
		System.out.println(request.getOrderId());
	}
}
