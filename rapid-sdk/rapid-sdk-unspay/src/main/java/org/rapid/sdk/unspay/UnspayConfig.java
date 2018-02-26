package org.rapid.sdk.unspay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Conditional(UnspayCondition.class)
@PropertySource("classpath:conf/unspay.properties")
public class UnspayConfig {

	private static String merchantId;
	private static String merchantKey;
	private static String gateWayAuthPay;		// 认证支付网关
	
	private static String urlAuthPay 			= "/authPay-front/authPay/pay";					// 认证支付 url
	private static String urlAuthPayRecode		= "/authPay-front/authPay/sendVercode ";		// 认证支付重发短验 url
	private static String urlAuthPayConfirm		= "/authPay-front/authPay/confirm";				// 认证支付确认 url
	private static String urlAuthPayQuery		= "/authPay-front/authPay/queryOrderStatus";	// 认证支付订单查询 url
	private static String urlAuthPayUnbind		= "/authPay-front/authPay/unbind";				// 认证支付解绑 url
	
	public static String getMerchantId() {
		return merchantId;
	}
	
	@Value("${unspay.merchantId}")
	public void setMerchantId(String merchantId) {
		UnspayConfig.merchantId = merchantId;
	}
	
	public static String getMerchantkey() {
		return merchantKey;
	}
	
	@Value("${unspay.merchantKey}")
	public void setMerchantKey(String merchantKey) {
		UnspayConfig.merchantKey = merchantKey;
	}
	
	public static String getGateWayAuthPay() {
		return gateWayAuthPay;
	}
	
	@Value("${unspay.gateWayAuthPay}")
	public void setGateWayAuthPay(String gateWayAuthPay) {
		UnspayConfig.gateWayAuthPay = gateWayAuthPay;
	}
	
	public static final String urlAuthPay() {
		return gateWayAuthPay + urlAuthPay;
	}
	
	public static final String urlAuthPayConfirm() {
		return gateWayAuthPay + urlAuthPayConfirm;
	}
	
	public static final String urlAuthPayRecode() {
		return gateWayAuthPay + urlAuthPayRecode;
	}
	
	public static final String urlAuthPayQuery() {
		return gateWayAuthPay + urlAuthPayQuery;
	}
	
	public static final String urlAuthPayUnbind() {
		return gateWayAuthPay + urlAuthPayUnbind;
	}
}
