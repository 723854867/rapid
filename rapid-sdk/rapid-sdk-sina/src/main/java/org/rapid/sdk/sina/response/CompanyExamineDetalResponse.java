package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class CompanyExamineDetalResponse extends SinaResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5175839349350181718L;

	
	@SerializedName("audit_result")
	private String auditResult;
	@SerializedName("audit_mgs")
	private String auditMgs;
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
	public String getExtendParam() {
		return extendParam;
	}
	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}

	

}
