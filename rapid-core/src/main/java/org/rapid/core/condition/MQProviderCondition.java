package org.rapid.core.condition;

import org.rapid.core.CoreConsts;

public class MQProviderCondition extends ConfigrationCondtion<String> {

	public MQProviderCondition() {
		super(CoreConsts.ACTIVEMQ_TYPE);
	}

	@Override
	protected boolean checkVal(String value) {
		return value.equalsIgnoreCase("provider");
	}
}
