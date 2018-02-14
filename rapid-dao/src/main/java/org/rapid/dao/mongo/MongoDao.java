package org.rapid.dao.mongo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rapid.core.bean.model.Identifiable;
import org.rapid.dao.Dao;
import org.rapid.dao.bean.model.Query;

import com.mongodb.client.model.Filters;

public class MongoDao<KEY, ENTITY extends Identifiable<KEY>> implements Dao<KEY, ENTITY> {
	
	protected static final String FIELD_ID					= "_id";
	
	@Resource
	protected Mongo mongo;
	protected String collection;
	protected Class<ENTITY> clazz;
	
	@SuppressWarnings("unchecked")
	public MongoDao(String collection) {
		this.collection = collection;
		Type superType = getClass().getGenericSuperclass();   
		Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
		this.clazz = (Class<ENTITY>) generics[1];
	}

	@Override
	public void insert(ENTITY entity) {
		mongo.insertOne(collection, entity);
	}

	@Override
	public void batchInsert(Collection<ENTITY> entities) {
		mongo.insertMany(collection, entities);
	}

	@Override
	public ENTITY getByKey(KEY key) {
		return mongo.findOne(collection, Filters.eq(FIELD_ID, key), clazz);
	}

	@Override
	public Map<KEY, ENTITY> getByKeys(Collection<KEY> keys) {
		return mongo.findMap(collection, MongoUtil.or(FIELD_ID, keys), clazz);
	}
	
	@Override
	public ENTITY queryUnique(Query query) {
		return null;
	}

	@Override
	public Map<KEY, ENTITY> query(Query query) {
		throw new UnsupportedOperationException("mongo db not support query!");
	}

	@Override
	public List<ENTITY> queryList(Query query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ENTITY entity) {
		return (int) mongo.replaceOne(collection, Filters.eq(FIELD_ID, entity.identity()), entity);
	}

	@Override
	public int updateMap(Map<KEY, ENTITY> entities) {
		return (int) mongo.bulkReplaceOne(collection, entities);
	}
	
	@Override
	public int updateCollection(Collection<ENTITY> entities) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByKey(KEY key) {
		return (int) mongo.deleteOne(collection, Filters.eq(FIELD_ID, key));
	}

	@Override
	public int deleteByKeys(Collection<KEY> keys) {
		return (int) mongo.deleteMany(collection, MongoUtil.or(FIELD_ID, keys));
	}
}
