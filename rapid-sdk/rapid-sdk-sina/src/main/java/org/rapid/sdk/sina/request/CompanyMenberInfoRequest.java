package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CompanyMenberInfoResponse;

public class CompanyMenberInfoRequest extends UserRequest<CompanyMenberInfoResponse> {

	private static final long serialVersionUID = 3501366535667470897L;

	public CompanyMenberInfoRequest() {
		super("新浪查询企业会员信息", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("query_member_infos");
	}

}
