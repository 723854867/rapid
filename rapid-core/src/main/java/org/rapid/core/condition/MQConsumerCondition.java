package org.rapid.core.condition;

import org.rapid.core.CoreConsts;

public class MQConsumerCondition extends ConfigrationCondtion<String> {

	public MQConsumerCondition() {
		super(CoreConsts.ACTIVEMQ_TYPE);
	}

	@Override
	protected boolean checkVal(String value) {
		return value.equals("consumer");
	}
}
