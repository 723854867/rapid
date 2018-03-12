package org.rapid.core.condition;

import org.rapid.core.CoreConsts;

public class ZookeeperCondition extends ConfigrationCondtion<Boolean> {

	public ZookeeperCondition() {
		super(CoreConsts.ZOOKEEPER_ENABLE);
	}

	@Override
	protected boolean checkVal(Boolean value) {
		return value;
	}
}
