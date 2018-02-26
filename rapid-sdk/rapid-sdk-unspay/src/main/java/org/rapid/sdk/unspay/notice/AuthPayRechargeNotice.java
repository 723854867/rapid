package org.rapid.sdk.unspay.notice;

import java.io.Serializable;
import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.sdk.unspay.UnspayConfig;

import com.google.gson.annotations.SerializedName;

/**
 * 认证支付充值回调
 * 
 * @author lynn
 */
public class AuthPayRechargeNotice implements Serializable {

	private static final long serialVersionUID = -7543909955822719390L;
	
	private static final String MAC_FORMAT = "accountId={0}&orderId={1}&amount={2}&result_code={3}&result_msg={4}&key={5}";

	@SerializedName("result_code")
	private String resultCode;
	@SerializedName("result_msg")
	private String resultMsg;
	private String amount;
	private String orderId;
	private String mac;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public boolean verify() {
		String mac = MessageFormat.format(MAC_FORMAT, UnspayConfig.getMerchantId(), orderId, amount, resultCode, resultMsg, UnspayConfig.getMerchantkey());
		mac = DigestUtils.md5Hex(mac).toUpperCase();
		return mac.equals(this.mac);
	}
}
