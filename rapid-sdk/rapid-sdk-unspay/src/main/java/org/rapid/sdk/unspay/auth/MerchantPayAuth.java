package org.rapid.sdk.unspay.auth;

import java.io.Serializable;
import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.sdk.unspay.UnspayConfig;
import org.rapid.util.DateUtil;

/**
 * 商户接口：商户 -> 银生订单支付请求
 * 
 * @author lynn
 */
public class MerchantPayAuth implements Serializable {

	private static final long serialVersionUID = 7233029649916400805L;
	
	private static final String MAC_FORMAT = "merchantId={0}&merchantUrl={1}&responseMode={2}&orderId={3}&currencyType={4}&amount={5}&assuredPay={6}&time={7}&remark={8}&merchantKey={9}";

	private String orderId;
	private String version = "3.0.0";
	private String merchantId;
	private String merchantUrl;
	private String responseMode = "2";
	private String currencyType = "CNY";
	// 两位小数
	private String amount;
	private String assuredPay = "false";
	private String time = DateUtil.getDate(DateUtil.yyyyMMddHHmmss);
	private String remark = "recharge";
	private String bankCode;
	private String b2b = "true";
	private String commodity = "recharge";
	private String orderUrl;
	private String cardAssured = "0";
	private String frontURL;
	private String mac;
	
	public MerchantPayAuth() {
		this.merchantId = UnspayConfig.getMerchantId();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantUrl() {
		return merchantUrl;
	}

	public void setMerchantUrl(String merchantUrl) {
		this.merchantUrl = merchantUrl;
	}

	public String getResponseMode() {
		return responseMode;
	}

	public void setResponseMode(String responseMode) {
		this.responseMode = responseMode;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAssuredPay() {
		return assuredPay;
	}

	public void setAssuredPay(String assuredPay) {
		this.assuredPay = assuredPay;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getB2b() {
		return b2b;
	}

	public void setB2b(String b2b) {
		this.b2b = b2b;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getOrderUrl() {
		return orderUrl;
	}

	public void setOrderUrl(String orderUrl) {
		this.orderUrl = orderUrl;
	}

	public String getCardAssured() {
		return cardAssured;
	}

	public void setCardAssured(String cardAssured) {
		this.cardAssured = cardAssured;
	}

	public String getFrontURL() {
		return frontURL;
	}

	public void setFrontURL(String frontURL) {
		this.frontURL = frontURL;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public void sign() {
		String macVal = MessageFormat.format(MAC_FORMAT, merchantId, merchantUrl, responseMode, orderId, currencyType, amount, assuredPay, time, remark, UnspayConfig.getMerchantkey());
		this.mac = DigestUtils.md5Hex(macVal).toUpperCase();
	}
}
