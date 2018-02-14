package org.rapid.dao.db.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import org.rapid.dao.db.mybatis.DaoAccessor;

public class InsertSQLProvider extends SQLProvider<String> {

	public InsertSQLProvider(Class<?> mapperClass, DaoAccessor mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	@Override
	public String effectiveSQL(MappedStatement ms) {
		return null;
	}
}
