package org.rapid.sdk.unspay.request;

import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.core.bean.enums.ContentType;
import org.rapid.core.http.HttpPost;
import org.rapid.core.serialize.GsonSerializer;
import org.rapid.sdk.unspay.UnspayConfig;
import org.rapid.sdk.unspay.response.AuthPayQueryResponse;

import com.google.gson.annotations.Expose;

public class AuthPayQueryRequest extends HttpPost<AuthPayQueryResponse> {

	private static final long serialVersionUID = 1629399303169527405L;
	
	private static final String MAC_FORMAT = "accountId={0}&orderId={1}&key={2}";

	@Expose
	private String accountId;
	@Expose
	private String orderId;
	@Expose
	private String mac;

	public AuthPayQueryRequest(String orderId) {
		super("银生宝认证支付订单查询", UnspayConfig.urlAuthPayQuery(), ContentType.APPLICATION_JSON_UTF_8, GsonSerializer.ANNOTATED, GsonSerializer.INSTANCE);
		this.orderId = orderId;
		this.accountId = UnspayConfig.getMerchantId();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	
	@Override
	protected byte[] serial() {
		String macVal = MessageFormat.format(MAC_FORMAT, accountId, orderId, UnspayConfig.getMerchantkey());
		this.mac = DigestUtils.md5Hex(macVal).toUpperCase();
		return this.serializer.serial(this);
	}
}
