package org.rapid.sdk.sina.request;

import org.rapid.core.IDWorker;
import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.AuditMemberInfosResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuditMemberInfosRequest extends UserRequest<AuditMemberInfosResponse> {

	private static final long serialVersionUID = -6273130569444155005L;

	// 请求审核订单号
	@Expose
	@SerializedName("audit_order_no")
	private String auditOrderNo;

	// 公司名称
	@Expose
	@SerializedName("company_name")
	private String companyName;


	// 企业地址
	@Expose
	@SerializedName("address")
	private String address;
	// 执照号
	@Expose
	@SerializedName("license_no")
	private String licenseNo;

	// 营业执照所在地
	@Expose
	@SerializedName("license_address")
	private String licenseAddress;
	// 执照过期日（营业期限）
	@Expose
	@SerializedName("license_expire_date")
	private String licenseExpireDate;

	// 营业范围
	@Expose
	@SerializedName("business_scope")
	private String businessScope;

	// 联系电话
	@Expose
	@SerializedName("telephone")
	private String telephone;

	// 联系Email
	@Expose
	@SerializedName("email")
	private String email;
	// 组织机构代码
	@Expose
	@SerializedName("organization_no")
	private String organizationNo;
	// 企业简介
	@Expose
	@SerializedName("summary")
	private String summary;
	// 企业法人
	@Expose
	@SerializedName("legal_person")
	private String legalPerson;

	// 法人证件号码
	@Expose
	@SerializedName("cert_no")
	private String certNo;
	// 证件类型
	@Expose
	@SerializedName("cert_type")
	private String certType;

	// 法人手机号码
	@Expose
	@SerializedName("legal_person_phone")
	private String legalPersonPhone;
	// 银行编号
	@Expose
	@SerializedName("bank_code")
	private String bankCode;
	// 银行卡号
	@Expose
	@SerializedName("bank_account_no")
	private String bankAccountNo;
	// 卡类型
	@Expose
	@SerializedName("card_type")
	private String cardType;

	// 卡属性
	@Expose
	@SerializedName("card_attribute")
	private String cardAttribute;
	// 开户行省份
	@Expose
	@SerializedName("province")
	private String province;
	// 开户行城市
	@Expose
	@SerializedName("city")
	private String city;
	// 支行名称
	@Expose
	@SerializedName("bank_branch")
	private String bankBranch;
	// 文件名称
	@Expose
	@SerializedName("fileName")
	private String fileName;

	// 文件摘要
	@Expose
	@SerializedName("digest")
	private String digest;
	// 文件摘要算法
	@Expose
	@SerializedName("digestType")
	private String digestType;
	// 请求者IP
	@Expose
	@SerializedName("client_ip")
	private String clientIp;

	public AuditMemberInfosRequest() {
		super("请求审核企业会员资质", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("audit_member_infos");
		this.auditOrderNo = IDWorker.INSTANCE.nextSid();
	}

	public String getAuditOrderNo() {
		return auditOrderNo;
	}

	public void setAuditOrderNo(String auditOrderNo) {
		this.auditOrderNo = auditOrderNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseAddress() {
		return licenseAddress;
	}

	public void setLicenseAddress(String licenseAddress) {
		this.licenseAddress = licenseAddress;
	}

	public String getLicenseExpireDate() {
		return licenseExpireDate;
	}

	public void setLicenseExpireDate(String licenseExpireDate) {
		this.licenseExpireDate = licenseExpireDate;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getLegalPersonPhone() {
		return legalPersonPhone;
	}

	public void setLegalPersonPhone(String legalPersonPhone) {
		this.legalPersonPhone = legalPersonPhone;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getDigestType() {
		return digestType;
	}

	public void setDigestType(String digestType) {
		this.digestType = digestType;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}


}
