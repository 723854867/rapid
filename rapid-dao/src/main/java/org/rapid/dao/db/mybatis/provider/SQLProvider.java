package org.rapid.dao.db.mybatis.provider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.mapping.MappedStatement;
import org.rapid.core.bean.exception.BizException;
import org.rapid.dao.db.mybatis.DaoAccessor;
import org.rapid.dao.db.mybatis.MsUtil;
import org.rapid.dao.db.mybatis.entity.EntityHelper;

public abstract class SQLProvider<T> {

	protected Class<?> mapperClass;
	protected DaoAccessor daoAccessor;
	protected Map<String, Class<?>> entityClassMap = new ConcurrentHashMap<String, Class<?>>();

	public SQLProvider(Class<?> mapperClass, DaoAccessor daoAccessor) {
		this.mapperClass = mapperClass;
		this.daoAccessor = daoAccessor;
	}

	public String dynamicSQL() {
		return "dynamicSQL";
	}

	/**
	 * 真实有效的 sql
	 */
	public abstract T effectiveSQL(MappedStatement ms);

	protected Class<?> getEntityClass(MappedStatement ms) {
		String msId = ms.getId();
		if (entityClassMap.containsKey(msId))
			return entityClassMap.get(msId);
		else {
			Class<?> mapperClass = MsUtil.getMapperClass(msId);
			Type[] types = mapperClass.getGenericInterfaces();
			for (Type type : types) {
				if (type instanceof ParameterizedType) {
					ParameterizedType t = (ParameterizedType) type;
					if (t.getRawType() == this.mapperClass || this.mapperClass.isAssignableFrom((Class<?>) t.getRawType())) {
						Class<?> returnType = (Class<?>) t.getActualTypeArguments()[1];
						EntityHelper.initEntityNameMap(returnType, daoAccessor.getConfig());
						entityClassMap.put(msId, returnType);
						return returnType;
					}
				}
			}
		}
		throw new BizException("无法获取 " + msId + " 方法的泛型信息!");
	}
}
