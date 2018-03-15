package org.rapid.sdk.sina.response;

import com.google.gson.annotations.SerializedName;

public class QueryMerchantConfigResponse extends SinaResponse {

	private static final long serialVersionUID = -436829389481383815L;

	@SerializedName("config_value")
	private String configValue;
	
	public String getConfigValue() {
		return configValue;
	}
	
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}
