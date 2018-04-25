package org.rapid.sdk.chuanglan;

import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.model.option.StrOption;

public class ChuangLanConfig {

	public static final StrOption PWD = new StrOption("chuanglan.pwd");
	public static final StrOption ACCOUNT = new StrOption("chuanglan.account");

	static {
		PWD.setDefaultValue(RapidConfiguration.get(PWD, true));
		ACCOUNT.setDefaultValue(RapidConfiguration.get(ACCOUNT, true));
	}
}
