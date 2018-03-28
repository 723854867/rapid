package org.rapid.sdk.unspay.request;

import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.core.IDWorker;
import org.rapid.core.bean.enums.ContentType;
import org.rapid.core.http.HttpPost;
import org.rapid.sdk.unspay.UnspayConfig;
import org.rapid.sdk.unspay.response.AuthPayRechargeResponse;
import org.rapid.util.StringUtil;
import org.rapid.util.serialize.GsonSerializer;

import com.google.gson.annotations.Expose;

/**
 * 认证支付:首次/再次充值请求
 * 
 * @author lynn
 */
public class AuthPayRechargeRequest extends HttpPost<AuthPayRechargeResponse> {

	private static final long serialVersionUID = 7984418816929048458L;
	
	private static final String MAC_FORMAT_1 = "accountId={0}&customerId={1}&payType={2}&name={3}&phoneNo={4}&cardNo={5}&idCardNo={6}&orderId={7}&purpose={8}&amount={9}&responseUrl={10}&key={11}";
	private static final String MAC_FORMAT_2 = "accountId={0}&customerId={1}&payType={2}&token={3}&orderId={4}&purpose={5}&amount={6}&responseUrl={7}&key={8}";

	// 商户编号
	@Expose
	private String accountId;
	// 用户编号
	@Expose
	private String customerId;
	// 支付类型
	@Expose
	private String payType = "0";
	// 授权码：如果是首次充值则填写四要素，非首次只需要填写token即可
	@Expose
	private String token;
	// 用户姓名
	@Expose
	private String name;
	// 手机号
	@Expose
	private String phoneNo;
	// 银行卡号
	@Expose
	private String cardNo;
	// 身份证号
	@Expose
	private String idCardNo;
	// 订单号
	@Expose
	private String orderId;
	// 目的
	@Expose
	private String purpose;
	// 金额(两位小数,且大于等于0.01)
	@Expose
	private String amount;
	// 响应地址
	@Expose
	private String responseUrl;
	// 数字签名(大写)
	@Expose
	private String mac;
	
	public AuthPayRechargeRequest() {
		this(IDWorker.INSTANCE.nextSid());
	}
	
	public AuthPayRechargeRequest(String orderId) {
		super("银生宝认证支付充值", UnspayConfig.urlAuthPay(), ContentType.APPLICATION_JSON_UTF_8, GsonSerializer.ANNOTATED, GsonSerializer.INSTANCE);
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

	public String getPayType() {
		return payType;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getResponseUrl() {
		return responseUrl;
	}

	public void setResponseUrl(String responseUrl) {
		this.responseUrl = responseUrl;
	}

	public String getMac() {
		return mac;
	}

	@Override
	protected byte[] serial() {
		String macVal = null;
		if (StringUtil.hasText(token)) {
			this.name = null;
			this.cardNo = null;
			this.phoneNo = null;
			this.idCardNo = null;
			this.payType = "1";
			macVal = MessageFormat.format(MAC_FORMAT_2, accountId, customerId, payType, token, orderId, purpose, amount, responseUrl, UnspayConfig.getMerchantkey());
		} else {
			this.token = null;
			this.payType = "0";
			macVal = MessageFormat.format(MAC_FORMAT_1, accountId, customerId, payType, name, phoneNo, cardNo, idCardNo, orderId, purpose, amount, responseUrl, UnspayConfig.getMerchantkey());
		}
		this.mac = DigestUtils.md5Hex(macVal).toUpperCase();
		return this.serializer.serial(this);
	}
}
