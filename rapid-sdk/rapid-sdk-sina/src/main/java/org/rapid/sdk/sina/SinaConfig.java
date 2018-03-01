package org.rapid.sdk.sina;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Conditional(SinaCondition.class)
@PropertySource("classpath:conf/sina.properties")
public class SinaConfig {

	// 微钱进公钥
	private static String pubKey;
	// 微钱进私钥
	private static String priKey;
	// 商户ID
	private static String merchantId;
	
	private static String gateWayMember				= "https://testgate.pay.sina.com.cn/mgs/gateway.do";
	private static String gateWayOrder				= "https://testgate.pay.sina.com.cn/mas/gateway.do";
	
	public static String getPubKey() {
		return pubKey;
	}
	
	@Value("${sina.pubKey}")
	public void setPubKey(String pubKey) {
		SinaConfig.pubKey = pubKey;
	}
	
	public static String getPriKey() {
		return priKey;
	}
	
	@Value("${sina.priKey}")
	public void setPriKey(String priKey) {
		SinaConfig.priKey = priKey;
	}
	
	public static String getGateWayOrder() {
		return gateWayOrder;
	}
	
	public static String getMerchantId() {
		return merchantId;
	}
	
	@Value("${sina.merchantId}")
	public void setMerchantId(String merchantId) {
		SinaConfig.merchantId = merchantId;
	}
	
	@Value("${sina.gateWayOrder}")
	public void setGateWayOrder(String gateWayOrder) {
		SinaConfig.gateWayOrder = gateWayOrder;
	}
	
	public static String getGateWayMember() {
		return gateWayMember;
	}
	
	@Value("${sina.gateWayMember}")
	public void setGateWayMember(String gateWayMember) {
		SinaConfig.gateWayMember = gateWayMember;
	}
}
