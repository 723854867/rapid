package org.rapid.dao.redis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rapid.core.bean.model.Identifiable;
import org.rapid.dao.Dao;
import org.rapid.dao.bean.model.Query;

@SuppressWarnings("unchecked")
public class RedisDao<KEY, ENTITY extends Identifiable<KEY>> implements Dao<KEY, ENTITY> {
	
	@Resource
	protected Redis redis;
	protected Class<ENTITY> clazz;
	
	public RedisDao() {
		Type superType = getClass().getGenericSuperclass();   
		Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
		this.clazz = (Class<ENTITY>) generics[1];
	}

	@Override
	public void insert(ENTITY entity) {
		
	}

	@Override
	public void batchInsert(Collection<ENTITY> entities) {
		
	}

	@Override
	public ENTITY getByKey(KEY key) {
		return null;
	}

	@Override
	public ENTITY queryUnique(Query query) {
		return null;
	}

	@Override
	public Map<KEY, ENTITY> query(Query query) {
		return null;
	}

	@Override
	public List<ENTITY> queryList(Query query) {
		return null;
	}

	@Override
	public Map<KEY, ENTITY> getByKeys(Collection<KEY> keys) {
		return null;
	}

	@Override
	public int update(ENTITY entity) {
		return 0;
	}

	@Override
	public int updateMap(Map<KEY, ENTITY> entities) {
		return 0;
	}

	@Override
	public int updateCollection(Collection<ENTITY> entities) {
		return 0;
	}

	@Override
	public int deleteByKey(KEY key) {
		return 0;
	}

	@Override
	public int deleteByKeys(Collection<KEY> keys) {
		return 0;
	}
}
