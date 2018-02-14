package org.rapid.dao.db.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import org.rapid.dao.db.mybatis.DaoAccessor;

public class BatchInsertSQLProvider extends SQLProvider<String> {

	public BatchInsertSQLProvider(Class<?> mapperClass, DaoAccessor mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	@Override
	public String effectiveSQL(MappedStatement ms) {
		// TODO Auto-generated method stub
		return null;
	}
}
