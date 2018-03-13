package org.rapid.dubbo;

import org.rapid.core.CoreConsts;
import org.rapid.core.condition.ConfigrationCondtion;

public class ProviderCondition extends ConfigrationCondtion<String> {

	protected ProviderCondition() {
		super(CoreConsts.DUBBO_APP_TYPE);
	}

	@Override
	protected boolean checkVal(String value) {
		return value.equalsIgnoreCase("both") || value.equalsIgnoreCase("provider");
	}
}
