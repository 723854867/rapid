package org.rapid.dao.db;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.rapid.core.bean.model.Identifiable;
import org.rapid.dao.Dao;
import org.rapid.dao.bean.model.Query;
import org.rapid.dao.db.mybatis.provider.BatchInsertSQLProvider;
import org.rapid.dao.db.mybatis.provider.DeleteByKeySQLProvider;
import org.rapid.dao.db.mybatis.provider.DeleteByKeysSQLProvider;
import org.rapid.dao.db.mybatis.provider.GetByKeySQLProvider;
import org.rapid.dao.db.mybatis.provider.GetByKeysSQLProvider;
import org.rapid.dao.db.mybatis.provider.InsertSQLProvider;
import org.rapid.dao.db.mybatis.provider.QueryListSQLProvider;
import org.rapid.dao.db.mybatis.provider.QuerySQLProvider;
import org.rapid.dao.db.mybatis.provider.QueryUniqueSQLProvider;
import org.rapid.dao.db.mybatis.provider.UpdateCollectionSQLProvider;
import org.rapid.dao.db.mybatis.provider.UpdateMapSQLProvider;
import org.rapid.dao.db.mybatis.provider.UpdateSQLProvider;

public interface DBDao<KEY, ENTITY extends Identifiable<KEY>> extends Dao<KEY, ENTITY> {

	@Override
	@InsertProvider(type = InsertSQLProvider.class, method = "dynamicSQL")
	void insert(ENTITY entity);
	
	@Override
	@InsertProvider(type = BatchInsertSQLProvider.class, method = "dynamicSQL")
	void batchInsert(Collection<ENTITY> entities);
	
	@Override
	@SelectProvider(type = GetByKeySQLProvider.class, method = "dynamicSQL")
	ENTITY getByKey(KEY key);
	
	@Override
	@SelectProvider(type = QueryUniqueSQLProvider.class, method = "dynamicSQL")
	ENTITY queryUnique(Query query);
	
	@Override
	@SelectProvider(type = QuerySQLProvider.class, method = "dynamicSQL")
	Map<KEY, ENTITY> query(Query query);
	
	@Override
	@SelectProvider(type = QueryListSQLProvider.class, method = "dynamicSQL")
	List<ENTITY> queryList(Query query);
	
	@Override
	@SelectProvider(type = GetByKeysSQLProvider.class, method = "dynamicSQL")
	Map<KEY, ENTITY> getByKeys(Collection<KEY> keys);
	
	@Override
	@UpdateProvider(type = UpdateSQLProvider.class, method = "dynamicSQL")
	int update(ENTITY entity);
	
	@Override
	@UpdateProvider(type = UpdateMapSQLProvider.class, method = "dynamicSQL")
	int updateMap(Map<KEY, ENTITY> entities);
	
	@Override
	@UpdateProvider(type = UpdateCollectionSQLProvider.class, method = "dynamicSQL")
	int updateCollection(Collection<ENTITY> entities);
	
	@Override
	@DeleteProvider(type = DeleteByKeySQLProvider.class, method = "dynamicSQL")
	int deleteByKey(KEY key);
	
	@Override
	@DeleteProvider(type = DeleteByKeysSQLProvider.class, method = "dynamicSQL")
	int deleteByKeys(Collection<KEY> keys);
}
