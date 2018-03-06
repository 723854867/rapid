package org.rapid.sdk.sina.request;

import org.rapid.core.IDWorker;
import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CardBindResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardBindRequest extends UserRequest<CardBindResponse> {

	private static final long serialVersionUID = -9196948315866644633L;

	// 请求编号
	@Expose
	@SerializedName("request_no")
	private String requestNo;
	//银行编号
	@Expose
	@SerializedName("bank_code")
	private String bankCode;
	// 银行卡号
	@Expose
	@SerializedName("bank_account_no")
	private String bankAccountNo;
	// 户名:可选
	@Expose
	@SerializedName("account_name")
	private String accountName;
	// 卡类型:DEBIT-借记卡；CREDIT-贷记卡(信用卡)
	@Expose
	@SerializedName("card_type")
	private String cardType = "DEBIT";
	// 卡属性:C-对私;B-对公
	@Expose
	@SerializedName("card_attribute")
	private String cardAttribute = "C";
	// 为空则使用实名认证中的证件信息
	@Expose
	@SerializedName("cert_type")
	private String certType;
	// 空则使用实名认证时实名信息
	@Expose
	@SerializedName("cert_no")
	private String certNo;
	@Expose
	@SerializedName("phone_no")
	private String phoneNo;
	// 信用卡专用，有效期(10/13)(月份/年份)
	@Expose
	@SerializedName("validity_period")
	private String validityPeriod;
	// 信用卡专用：密码
	@Expose
	@SerializedName("verification_value")
	private String verificationValue;
	// 省份
	@Expose
	private String province;
	// 城市
	@Expose
	private String city;
	@Expose
	@SerializedName("bank_branch")
	private String bankBranch;
	// 银行卡真实性认证方式，空则表示不认证：SING-签约认证
	@Expose
	@SerializedName("verify_mode")
	private String verifyMode = "SIGN";
	@Expose
	@SerializedName("client_ip")
	private String clientIp;

	public CardBindRequest() {
		super("新浪绑定银行卡", SinaConfig.getGateWayMember());
		setService("binding_bank_card");
		this.requestNo = IDWorker.INSTANCE.nextSid();
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardAttribute() {
		return cardAttribute;
	}

	public void setCardAttribute(String cardAttribute) {
		this.cardAttribute = cardAttribute;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public String getVerificationValue() {
		return verificationValue;
	}

	public void setVerificationValue(String verificationValue) {
		this.verificationValue = verificationValue;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getVerifyMode() {
		return verifyMode;
	}

	public void setVerifyMode(String verifyMode) {
		this.verifyMode = verifyMode;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
