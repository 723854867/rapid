package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.enums.UserAction;
import org.rapid.sdk.sina.response.SinaResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequest<RESPONSE extends SinaResponse> extends SinaRequest<RESPONSE> {

	private static final long serialVersionUID = -3466660970526319993L;
	
	// 用户标识信息(商户的用户id)：
	@Expose
	@SerializedName("identity_id")
	private String identityId;
	// 用户标识类型：
	@Expose
	@SerializedName("identity_type")
	private String identityType = "UID";
	@Expose
	@SerializedName("extend_param")
	private String extendParam;
	
	public UserRequest(String prefix, String url) {
		super(prefix, url);
	}
	
	public UserRequest(UserAction action, String url) {
		super(action.prefix(), url);
	}
	
	public String getIdentityId() {
		return identityId;
	}
	
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	
	public String getIdentityType() {
		return identityType;
	}
	
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	
	public String getExtendParam() {
		return extendParam;
	}
	
	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}
}
