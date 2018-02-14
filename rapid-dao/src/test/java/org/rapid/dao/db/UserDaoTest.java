package org.rapid.dao.db;

import javax.annotation.Resource;

import org.junit.Test;
import org.rapid.dao.DaoTest;
import org.rapid.dao.bean.User;
import org.rapid.dao.db.dao.UserDao;

public class UserDaoTest extends DaoTest {

	@Resource
	private UserDao userDao;
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setAge(10);
		user.setName("test");
		userDao.insert(user);
		System.out.println(user.getId());
	}
}
