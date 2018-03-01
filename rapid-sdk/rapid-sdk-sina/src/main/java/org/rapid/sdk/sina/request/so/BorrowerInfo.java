package org.rapid.sdk.sina.request.so;

import java.io.Serializable;

public class BorrowerInfo implements Serializable {

	private static final long serialVersionUID = -3779992741709861946L;

	// 借款人编号
	private String id;
	// 编号类型
	private String type = "UID";
	// 借款金额
	private String amount;
	// 借款用途
	private String purpose;
	// 手机号
	private String mobile;
	// 固定电话
	private String telephone;
	// 所在单位
	private String company;
	// 工作年限
	private String workYears;
	// 借款人月收入
	private String mothIncome;
	// 借款人学历
	private String education;
	// 借款人婚姻状况
	private String marital;
	// 借款人地址
	private String address;
	private String email;
	// 摘要
	private String summary;
	// 扩展参数
	private String extend;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWorkYears() {
		return workYears;
	}

	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}

	public String getMothIncome() {
		return mothIncome;
	}

	public void setMothIncome(String mothIncome) {
		this.mothIncome = mothIncome;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}
}
