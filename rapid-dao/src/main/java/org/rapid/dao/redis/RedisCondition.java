package org.rapid.dao.redis;

import org.rapid.core.CoreConsts;
import org.rapid.core.condition.ConfigrationCondtion;

public class RedisCondition extends ConfigrationCondtion<Boolean> {

	public RedisCondition() {
		super(CoreConsts.REDIS_ENABLE);
	}

	@Override
	protected boolean checkVal(Boolean value) {
		return value;
	}
}
