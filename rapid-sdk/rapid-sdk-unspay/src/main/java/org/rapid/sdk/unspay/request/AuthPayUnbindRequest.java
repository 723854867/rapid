package org.rapid.sdk.unspay.request;

import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.core.bean.enums.ContentType;
import org.rapid.core.http.HttpPost;
import org.rapid.core.serialize.GsonSerializer;
import org.rapid.sdk.unspay.UnspayConfig;
import org.rapid.sdk.unspay.response.AuthPayUnbindResponse;

import com.google.gson.annotations.Expose;

/**
 * 认证支付：解绑
 * 
 * @author lynn
 */
public class AuthPayUnbindRequest extends HttpPost<AuthPayUnbindResponse> {

	private static final long serialVersionUID = 3930642208224743649L;
	
	private static final String MAC_FORMAT = "accountId={0}&customerId={1}&token={2}&key={3}";

	@Expose
	private String accountId;
	@Expose
	private String customerId;
	@Expose
	private String token;
	@Expose
	private String mac;

	public AuthPayUnbindRequest(String token, String customerId) {
		super("银生宝认证支付解绑", UnspayConfig.urlAuthPayUnbind(), ContentType.APPLICATION_JSON_UTF_8, GsonSerializer.ANNOTATED, GsonSerializer.INSTANCE);
		this.token = token;
		this.customerId = customerId;
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

	public String getToken() {
		return token;
	}

	public String getMac() {
		return mac;
	}
	
	@Override
	protected byte[] serial() {
		String macVal = MessageFormat.format(MAC_FORMAT, accountId, customerId, token, UnspayConfig.getMerchantkey());
		this.mac = DigestUtils.md5Hex(macVal).toUpperCase();
		return this.serializer.serial(this);
	}
}
