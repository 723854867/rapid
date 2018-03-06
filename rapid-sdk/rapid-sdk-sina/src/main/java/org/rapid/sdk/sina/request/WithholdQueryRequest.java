package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.WithholdQueryResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithholdQueryRequest extends UserRequest<WithholdQueryResponse> {

	private static final long serialVersionUID = -4576087402858063550L;

	@Expose
	@SerializedName("auth_type")
	private String authType = "ACCOUNT";
	@Expose
	@SerializedName("auth_sub_type")
	private String authSubType;
	@Expose
	@SerializedName("is_detail_disp")
	private String isDetailDisp = "N";

	public WithholdQueryRequest() {
		super("新浪查看用户是否委托扣款", SinaConfig.getGateWayMember());
		setService("query_withhold_authority");
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthSubType() {
		return authSubType;
	}

	public void setAuthSubType(String authSubType) {
		this.authSubType = authSubType;
	}

	public String getIsDetailDisp() {
		return isDetailDisp;
	}

	public void setIsDetailDisp(String isDetailDisp) {
		this.isDetailDisp = isDetailDisp;
	}
}
