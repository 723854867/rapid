package org.rapid.sdk.unspay.notice;

import java.io.Serializable;
import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.sdk.unspay.UnspayConfig;

/**
 * 商户充值回调
 * 
 * @author lynn
 */
public class MerchantPayNotice implements Serializable {

	private static final long serialVersionUID = 6086158093746012815L;
	
	private static final String MAC_FORMAT = "merchantId={0}&responseMode={1}&orderId={2}&currencyType={3}&amount={4}&returnCode={5}&returnMessage={6}&merchantKey={7}";

	private String orderId;
	private String merchantId;
	private String responseMode;
	private String currencyType = "CNY";
	private String amount;
	private String bankCode;
	private String returnCode;
	private String returnMessage;
	private String mac;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public boolean verify() {
		String mac = MessageFormat.format(MAC_FORMAT, UnspayConfig.getMerchantId(), responseMode, getOrderId(), currencyType, amount, returnCode, returnMessage, UnspayConfig.getMerchantkey());
		mac = DigestUtils.md5Hex(mac).toUpperCase();
		return mac.equals(this.mac);
	}
}
