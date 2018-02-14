package org.rapid.dao.db.mybatis.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Table;

public class EntityTable {
	
	private String name;
    private String schema;
    private String catalog;
	private Class<?> entityClass;
	
	//实体类 => 全部列属性
    private Set<EntityColumn> entityClassColumns;
    //实体类 => 主键信息
    private Set<EntityColumn> entityClassPKColumns;

	public EntityTable(Class<?> entityClass) {
        this.entityClass = entityClass;
        this.entityClassColumns = new LinkedHashSet<EntityColumn>();
        this.entityClassPKColumns = new LinkedHashSet<EntityColumn>();
    }
	
	public void setName(String name) {
        this.name = name;
    }
	
	public void setTable(Table table) {
        this.name = table.name();
        this.schema = table.schema();
        this.catalog = table.catalog();
    }
	
	public Set<EntityColumn> getEntityClassColumns() {
        return entityClassColumns;
    }

    public void setEntityClassColumns(Set<EntityColumn> entityClassColumns) {
        this.entityClassColumns = entityClassColumns;
    }

    public Set<EntityColumn> getEntityClassPKColumns() {
        return entityClassPKColumns;
    }

    public void setEntityClassPKColumns(Set<EntityColumn> entityClassPKColumns) {
        this.entityClassPKColumns = entityClassPKColumns;
    }
}
