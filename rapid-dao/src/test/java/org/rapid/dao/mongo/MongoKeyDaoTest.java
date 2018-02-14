package org.rapid.dao.mongo;

import javax.annotation.Resource;

import org.junit.Test;

public class MongoKeyDaoTest extends MongoTest {

	@Resource
	private MongoKeyDao mongoKeyDao;
	
	@Test
	public void testGetAndInc() { 
		long value = 0;
		while(value < 100)
			value = mongoKeyDao.getAndInc("kes2", 1);
	}
}
