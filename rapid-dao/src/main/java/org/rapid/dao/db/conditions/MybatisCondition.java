package org.rapid.dao.db.conditions;

import org.rapid.core.CoreConsts;
import org.rapid.core.condition.ConfigrationCondtion;

public class MybatisCondition extends ConfigrationCondtion<String> {

	public MybatisCondition() {
		super(CoreConsts.DB_ORM_TYPE);
	}

	@Override
	protected boolean checkVal(String value) {
		return value.equalsIgnoreCase("mybatis");
	}
}
