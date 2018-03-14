package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CompanyExamineDetalResponse;

public class CompanyExamineDetalRequest extends UserRequest<CompanyExamineDetalResponse> {

	private static final long serialVersionUID = 3501366535667470897L;

	public CompanyExamineDetalRequest() {
		super("新浪查询企业会员审核结果", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("query_audit_result");
	}

}
