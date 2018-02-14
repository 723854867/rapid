package org.rapid.dao.mongo;

import javax.annotation.Resource;

import org.junit.Test;
import org.rapid.dao.DaoTest;
import org.rapid.dao.bean.User;
import org.rapid.dao.mongo.Mongo;

public class MongoTest extends DaoTest {

	@Resource
	private Mongo mongo;
	
	@Test
	public void testInsert() {
		User u = new User();
		u.setId(10);
		u.setAge(100);
		u.setName("iris");
		mongo.insertOne("user", u);
	}
}
