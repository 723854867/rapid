package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.SinaResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantConfigRequest extends SinaRequest<SinaResponse> {

	private static final long serialVersionUID = -3283330717200173281L;

	@Expose
	@SerializedName("config_key")
	private String configKey;
	@Expose
	@SerializedName("config_value")
	private String configValue;
	@Expose
	@SerializedName("extend_param")
	private String extendParam;

	public MerchantConfigRequest() {
		super("新浪修改商户配置", SinaConfig.GATEWAY_MEMBER.getDefaultValue());
		setService("set_merchant_config");
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}
}
