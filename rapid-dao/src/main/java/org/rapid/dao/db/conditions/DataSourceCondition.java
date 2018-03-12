package org.rapid.dao.db.conditions;

import org.rapid.core.CoreConsts;
import org.rapid.core.condition.ConfigrationCondtion;

public class DataSourceCondition extends ConfigrationCondtion<String> {
	
	public DataSourceCondition() {
		super(CoreConsts.DB_DATASOURCE_CLASS);
	}

	@Override
	protected boolean checkVal(String value) {
		try {
			Class.forName(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
