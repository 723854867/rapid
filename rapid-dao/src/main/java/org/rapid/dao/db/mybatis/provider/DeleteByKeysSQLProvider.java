package org.rapid.dao.db.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import org.rapid.dao.db.mybatis.DaoAccessor;

public class DeleteByKeysSQLProvider extends SQLProvider<String> {

	public DeleteByKeysSQLProvider(Class<?> mapperClass, DaoAccessor mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	@Override
	public String effectiveSQL(MappedStatement ms) {
		// TODO Auto-generated method stub
		return null;
	}
}
