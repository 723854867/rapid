package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class CompanyMenberInfoResponse extends SinaResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5175839349350181718L;

	@SerializedName("member_type")
	private String auditResult;

	@SerializedName("website")
	private String auditMgs;

	@SerializedName("company_name")
	private String companyName;
	
	@SerializedName("address")
	private String address;
	
	@SerializedName("license_no")
	private String licenseNo;
	
	@SerializedName("license_address")
	private String licenseAddress;
	
	@SerializedName("license_expire_date")
	private String licenseExpireDate;
	
	@SerializedName("business_scope")
	private String businessScope;
	
	@SerializedName("email")
	private String email;
	
	@SerializedName("organization_no")
	private String organizationNo;
	
	@SerializedName("summary")
	private String summary;
	
	@SerializedName("legal_person")
	private String legalPerson;
	
	@SerializedName("cert_no")
	private String certNo;
	
	@SerializedName("cert_type")
	private String certType;
	
	@SerializedName("legal_person_phone")
	private String legalPersonPhone;
	
	@SerializedName("extend_param")
	private String extendParam;

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getAuditMgs() {
		return auditMgs;
	}

	public void setAuditMgs(String auditMgs) {
		this.auditMgs = auditMgs;
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

	public String getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}

	
	
}
