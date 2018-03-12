package org.rapid.dao.mongo;

import org.rapid.core.CoreConsts;
import org.rapid.core.condition.ConfigrationCondtion;

/**
 * 只要有一个模块开启 mongo就会启用mongo
 * 
 * @author lynn
 */
public class MongoCondition extends ConfigrationCondtion<Boolean> {

	public MongoCondition() {
		super(CoreConsts.MONGO_ENABLE);
	}

	@Override
	protected boolean checkVal(Boolean value) {
		return value;
	}
}
