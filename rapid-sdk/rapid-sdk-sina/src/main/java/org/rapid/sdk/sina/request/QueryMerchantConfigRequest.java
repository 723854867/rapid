package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.QueryMerchantConfigResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryMerchantConfigRequest extends SinaRequest<QueryMerchantConfigResponse> {

	private static final long serialVersionUID = -2897631693345252117L;
	
	@Expose
	@SerializedName("config_key")
	private String configKey;

	public QueryMerchantConfigRequest() {
		super("新浪查询商户配置", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("query_merchant_config");
	}
	
	public String getConfigKey() {
		return configKey;
	}
	
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
}
