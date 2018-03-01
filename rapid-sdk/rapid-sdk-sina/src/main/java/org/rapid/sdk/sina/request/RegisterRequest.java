package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.SinaResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 创建激活会员
 * 
 * @author lynn
 */
public class RegisterRequest extends UserRequest<SinaResponse> {

	private static final long serialVersionUID = -678889696103067343L;

	// 会员类型：
	@Expose
	@SerializedName("member_type")
	private String memberType;
	@Expose
	@SerializedName("client_ip")
	private String clientIp;

	public RegisterRequest() {
		super("新浪创建激活会员", SinaConfig.getGateWayMember());
		setService("create_activate_member");
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
