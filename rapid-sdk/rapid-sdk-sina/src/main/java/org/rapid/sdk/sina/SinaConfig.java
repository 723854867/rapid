package org.rapid.sdk.sina;

import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.model.option.StrOption;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(SinaCondition.class)
public class SinaConfig {

	// 微钱进公钥
	public static final StrOption PUB_KEY = new StrOption("sina.pubKey");
	// 微钱进私钥
	public static final StrOption PRI_KEY = new StrOption("sina.priKey");
	// 商户ID
	public static final StrOption MERCHANT_ID = new StrOption("sina.merchantId");

	public static final StrOption GATEWAY_ORDER = new StrOption("sina.gatewayOrder","https://testgate.pay.sina.com.cn/mas/gateway.do");
	public static final StrOption GATEWAY_MEMBER = new StrOption("sina.gatewayMember","https://testgate.pay.sina.com.cn/mgs/gateway.do");

	public static final StrOption HOST = new StrOption("sina.host");
	public static final StrOption PORT = new StrOption("sina.port");
	public static final StrOption DIRECTORY = new StrOption("sina.directory");
	public static final StrOption PRIVATEKEY = new StrOption("sina.privateKey");

	static {
		PUB_KEY.setDefaultValue(RapidConfiguration.get(PUB_KEY, true));
		PRI_KEY.setDefaultValue(RapidConfiguration.get(PRI_KEY, true));
		MERCHANT_ID.setDefaultValue(RapidConfiguration.get(MERCHANT_ID, true));
		String gatewayOrder = RapidConfiguration.get(GATEWAY_ORDER, false);
		if (null != gatewayOrder)
			GATEWAY_ORDER.setDefaultValue(gatewayOrder);
		String gatewayMember = RapidConfiguration.get(GATEWAY_MEMBER, false);
		if (null != gatewayMember)
			GATEWAY_MEMBER.setDefaultValue(gatewayMember);
		
		HOST.setDefaultValue(RapidConfiguration.get(HOST, true));
		PORT.setDefaultValue(RapidConfiguration.get(PORT, true));
		DIRECTORY.setDefaultValue(RapidConfiguration.get(DIRECTORY, true));
		PRIVATEKEY.setDefaultValue(RapidConfiguration.get(PRIVATEKEY, true));
		
	}
}
