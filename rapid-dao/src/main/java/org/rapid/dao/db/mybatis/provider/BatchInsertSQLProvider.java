package org.rapid.dao.db.mybatis.provider;

import java.util.Set;

import javax.persistence.GeneratedValue;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.rapid.dao.db.mybatis.DaoAccessor;
import org.rapid.dao.db.mybatis.MultipleJdbc3KeyGenerator;
import org.rapid.dao.db.mybatis.entity.EntityColumn;
import org.rapid.dao.db.mybatis.entity.EntityHelper;
import org.rapid.dao.db.mybatis.entity.EntityTable;

public class BatchInsertSQLProvider extends SQLProvider<String> {

	public BatchInsertSQLProvider(Class<?> mapperClass, DaoAccessor mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	@Override
	public String effectiveSQL(MappedStatement ms, Class<?> daoClass) {
		EntityTable table = getEntityTable(ms);
        Class<?> entityClass = table.getEntityClass();
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(insertIntoTable(entityClass, tableName(entityClass)));
        sql.append(insertColumns(entityClass));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        for (EntityColumn column : columnList) {
            if (column.isInsertable()) 
                sql.append(column.getColumnHolder("record") + ",");
        }
        sql.append("</trim>");
        sql.append("</foreach>");
        // 主键自增处理
        if (table.getpKColumn().getEntityField().isAnnotationPresent(GeneratedValue.class)) {
        	  MetaObject msObject = SystemMetaObject.forObject(ms);
              msObject.setValue("keyGenerator", new MultipleJdbc3KeyGenerator());
        }
        return sql.toString();
    }
}
