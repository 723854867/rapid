package org.rapid.sdk.unspay.request;

import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.core.Assert;
import org.rapid.core.bean.enums.ContentType;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.http.HttpPost;
import org.rapid.core.serialize.GsonSerializer;
import org.rapid.sdk.unspay.UnspayConfig;
import org.rapid.sdk.unspay.response.AuthPayRecodeResponse;

import com.google.gson.annotations.Expose;

/**
 * 认证支付：重发短验
 * 
 * @author lynn
 */
public class AuthPayReCodeRequest extends HttpPost<AuthPayRecodeResponse> {

	private static final long serialVersionUID = -3400903884130274663L;
	
	private static final String MAC_FORMAT = "accountId={0}&customerId={1}&token={2}&orderId={3}&phoneNo={4}&key={5}";

	@Expose
	private String accountId;
	@Expose
	private String customerId;
	@Expose
	private String orderId;
	@Expose
	private String phoneNo;
	@Expose
	private String token;
	@Expose
	private String mac;
	
	public AuthPayReCodeRequest(String token, String orderId) {
		super("银生宝认证支付重发短验" ,UnspayConfig.urlAuthPayRecode(), ContentType.APPLICATION_JSON_UTF_8, GsonSerializer.ANNOTATED, GsonSerializer.INSTANCE);
		this.token = token;
		this.orderId = orderId;
		this.accountId = UnspayConfig.getMerchantId();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Override
	protected byte[] serial() {
		Assert.notNull(Code.SYS_ERROR, "phoneNo 不能为空", phoneNo);
		Assert.notNull(Code.SYS_ERROR, "customerId 不能为空", customerId);
		String macVal = MessageFormat.format(MAC_FORMAT, accountId, customerId, token, orderId, phoneNo, UnspayConfig.getMerchantkey());
		this.mac = DigestUtils.md5Hex(macVal).toUpperCase();
		return this.serializer.serial(this);
	}
}
