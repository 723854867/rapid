package org.rapid.sdk.unspay.request;

import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.core.bean.enums.ContentType;
import org.rapid.core.http.HttpPost;
import org.rapid.sdk.unspay.UnspayConfig;
import org.rapid.sdk.unspay.response.AuthPayConfirmResponse;
import org.rapid.util.serialize.GsonSerializer;

import com.google.gson.annotations.Expose;

/**
 * 认证支付:确认支付
 * 
 * @author lynn
 */
public class AuthPayConfirmRequest extends HttpPost<AuthPayConfirmResponse> {

	private static final long serialVersionUID = -1515346121801638911L;
	
	private static final String MAC_FORMAT = "accountId={0}&customerId={1}&token={2}&orderId={3}&vericode={4}&key={5}";

	@Expose
	private String accountId;
	@Expose
	private String customerId;
	@Expose
	private String orderId;
	// 短信验证码
	@Expose
	private String vericode;
	@Expose
	private String token;
	@Expose
	private String mac;
	
	public AuthPayConfirmRequest(String token, String orderId) {
		super("银生宝认证支付确认充值", UnspayConfig.urlAuthPayConfirm(), ContentType.APPLICATION_JSON_UTF_8, GsonSerializer.ANNOTATED, GsonSerializer.INSTANCE);
		this.token = token;
		this.orderId = orderId;
		this.accountId = UnspayConfig.getMerchantId();
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getVericode() {
		return vericode;
	}

	public void setVericode(String vericode) {
		this.vericode = vericode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMac() {
		return mac;
	}
	
	@Override
	protected byte[] serial() {
		String macVal = MessageFormat.format(MAC_FORMAT, accountId, customerId, token, orderId, vericode, UnspayConfig.getMerchantkey());
		this.mac = DigestUtils.md5Hex(macVal).toUpperCase();
		return this.serializer.serial(this);
	}
}
