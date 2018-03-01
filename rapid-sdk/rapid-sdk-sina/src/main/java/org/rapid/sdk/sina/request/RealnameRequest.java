package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.SinaResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealnameRequest extends UserRequest<SinaResponse> {

	private static final long serialVersionUID = 8786341791001824522L;

	@Expose
	@SerializedName("real_name")
	private String realName;
	@Expose
	@SerializedName("cert_type")
	private String certType = "IC";
	@Expose
	@SerializedName("cert_no")
	private String certNo;
	@Expose
	@SerializedName("need_confirm")
	private String needConfirm = "Y";
	@Expose
	@SerializedName("client_ip")
	private String clientIp;

	public RealnameRequest() {
		super("新浪设置实名信息", SinaConfig.getGateWayMember());
		setService("set_real_name");
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getNeedConfirm() {
		return needConfirm;
	}

	public void setNeedConfirm(String needConfirm) {
		this.needConfirm = needConfirm;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
