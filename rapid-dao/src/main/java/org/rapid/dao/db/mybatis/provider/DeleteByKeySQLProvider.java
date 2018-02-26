package org.rapid.dao.db.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import org.rapid.dao.db.mybatis.DaoAccessor;

public class DeleteByKeySQLProvider extends SQLProvider<String> {

	public DeleteByKeySQLProvider(Class<?> mapperClass, DaoAccessor mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	@Override
	public String effectiveSQL(MappedStatement ms, Class<?> daoClass) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(deleteFromTable(entityClass, tableName(entityClass)));
        sql.append(wherePKColumn(entityClass));
        return sql.toString();
    }
}
