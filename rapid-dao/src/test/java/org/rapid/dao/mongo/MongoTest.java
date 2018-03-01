package org.rapid.dao.mongo;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.rapid.core.IDWorker;
import org.rapid.core.serialize.SerializeUtil;
import org.rapid.dao.DaoTest;
import org.rapid.dao.bean.SuperUser;
import org.rapid.dao.bean.WrapUser;

import com.mongodb.client.model.Filters;

public class MongoTest extends DaoTest {

	@Resource
	private Mongo mongo;
	
	@Test
	@SuppressWarnings("unchecked")
	public void testInsert() {
		WrapUser wu = new WrapUser();
		wu.set_id(IDWorker.INSTANCE.nextSid());
		SuperUser u = new SuperUser();
		u.setId(10);
		u.setAge(100);
		u.setName("iris");
		u.setAddress("ssss");
		u.setPrivillege(102);
		wu.setUser(u);
		mongo.insertOne("user", wu);
		System.out.println(wu.get_id());
		wu = mongo.findOne("user", Filters.eq("_id", wu.get_id()), WrapUser.class);
		Map<String, String> map = (Map<String, String>) wu.getUser();
		String json = SerializeUtil.GSON.toJson(map);
		u = SerializeUtil.GSON.fromJson(json, SuperUser.class);
		System.out.println(u);
	}
}
