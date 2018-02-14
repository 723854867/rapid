package org.rapid.dao.db.mybatis.entity;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Table;

import org.rapid.dao.bean.annotations.NameStyle;
import org.rapid.dao.db.mybatis.DaoConfig;
import org.rapid.dao.db.mybatis.DaoConfig.Style;
import org.rapid.util.StringUtil;

public class EntityHelper {
	
    // 实体类 => 表对象
    private static final Map<Class<?>, EntityTable> entityTableMap = new ConcurrentHashMap<Class<?>, EntityTable>();

	/**
	 * 初始化实体属性
	 */
	public static synchronized void initEntityNameMap(Class<?> entityClass, DaoConfig config) {
		if (entityTableMap.get(entityClass) != null)
			return;
		Style style = config.getNameStyle();
		if (entityClass.isAnnotationPresent(NameStyle.class)) {			// style，该注解优先于全局配置
			NameStyle nameStyle = entityClass.getAnnotation(NameStyle.class);
			style = nameStyle.value();
		}

		// 创建并缓存EntityTable
		EntityTable entityTable = null;
		if (entityClass.isAnnotationPresent(Table.class)) {
			Table table = entityClass.getAnnotation(Table.class);
			if (!table.name().equals("")) {
				entityTable = new EntityTable(entityClass);
				entityTable.setTable(table);
			}
		}
		if (entityTable == null) {
			entityTable = new EntityTable(entityClass);
			String className = entityClass.getSimpleName();
			switch (style) {
			case camelhump:
				entityTable.setName(StringUtil.camel2Underline(className));
				break;
			case uppercase:
				entityTable.setName(className.toUpperCase());
				break;
			case lowercase:
				entityTable.setName(className.toLowerCase());
				break;
			case camelhumpAndLowercase:
				entityTable.setName(StringUtil.camel2Underline(className).toLowerCase());
				break;
			case camelhumpAndUppercase:
				entityTable.setName(StringUtil.camel2Underline(className).toUpperCase());
				break;
			default:
				entityTable.setName(className);
				break;
			}
			
		}
		// 处理所有列
		List<EntityField> fields = null;
		if (config.isEnableMethodAnnotation()) {
			fields = FieldHelper.getAll(entityClass);
		} else {
			fields = FieldHelper.getFields(entityClass);
		}
		for (EntityField field : fields) {
			// 如果启用了简单类型，就做简单类型校验，如果不是简单类型，直接跳过
			// 3.5.0 如果启用了枚举作为简单类型，就不会自动忽略枚举类型
			if (config.isUseSimpleType() && !(SimpleTypeUtil.isSimpleType(field.getJavaType())
					|| (config.isEnumAsSimpleType() && Enum.class.isAssignableFrom(field.getJavaType())))) {
				continue;
			}
			processField(entityTable, style, field, config.getWrapKeyword());
		}
		// 当pk.size=0的时候使用所有列作为主键
		if (entityTable.getEntityClassPKColumns().size() == 0) {
			entityTable.setEntityClassPKColumns(entityTable.getEntityClassColumns());
		}
		entityTable.initPropertyMap();
		entityTableMap.put(entityClass, entityTable);
	}
}
