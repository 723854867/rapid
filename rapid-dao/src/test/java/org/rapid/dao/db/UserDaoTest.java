package org.rapid.dao.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.rapid.dao.DaoTest;
import org.rapid.dao.bean.User;
import org.rapid.dao.bean.model.Query;
import org.rapid.dao.db.dao.UserDao;

public class UserDaoTest extends DaoTest {

	@Resource
	private Tx tx;
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
	
	@Test
	public void testInserTx() {
		User user = new User();
		user.setAge(10);
		user.setName("test");
		tx.insert(user);
		System.out.println(user.getId());
	}
	
	@Test
	public void testBatchInsert() {
		List<User> list = new ArrayList<User>();
		User user = new User();
//		user.setId(1);
		user.setAge(10);
		user.setName("10");
		list.add(user);
		user = new User();
//		user.setId(2);
		user.setAge(50);
		user.setName("50");
		list.add(user);
		user = new User();
//		user.setId(3);
		user.setAge(100);
		user.setName("100");
		list.add(user);
		user = new User();
//		user.setId(4);
		user.setAge(125);
		user.setName("125");
		list.add(user);
		userDao.batchInsert(list);
		for (User temp : list)
			System.out.println(temp.getId() + " " + temp.getAge() + " " + temp.getName());
	}
	
	@Test
	public void testGetByKey() {
		User user = userDao.getByKey(1);
		if (null != user)
			System.out.println(user.getId() + " " + user.getAge() + " " + user.getName());
	}
	
	@Test
	public void testGetByKeys() {
		Set<Integer> set = new HashSet<Integer>() {
			private static final long serialVersionUID = 1L;
			{
				add(1);
				add(2);
				add(117);
				add(116);
				add(2310);
			}
		};
		Map<Integer, User> users = userDao.getByKeys(set);
		System.out.println(users.size());
	}
	
	@Test
	public void testUpdate() {
		User user = userDao.getByKey(1);
		System.out.println(user.getId() + " " + user.getAge() + " " + user.getName());
		user.setAge(127);
		user.setName("张三丰");
		System.out.println(userDao.update(user));
		user = userDao.getByKey(1);
		System.out.println(user.getId() + " " + user.getAge() + " " + user.getName());
	}
	
	@Test
	public void testUpdateMap() {
		Set<Integer> set = new HashSet<Integer>() {
			private static final long serialVersionUID = 1L;
			{
				add(27);
				add(118);
			}
		};
		Map<Integer, User> users = userDao.getByKeys(set);
		for (User user : users.values()) 
			user.setName("李寻欢");
		User user = new User();
		user.setId(2350);
		user.setAge(125);
		user.setName("测试");
		users.put(user.getId(), user);
		System.out.println(userDao.updateMap(users));
	}
	
	@Test
	public void testUpdateCollection() {
		Set<Integer> set = new HashSet<Integer>() {
			private static final long serialVersionUID = 1L;
			{
				add(27);
				add(118);
			}
		};
		Map<Integer, User> users = userDao.getByKeys(set);
		for (User user : users.values()) 
			user.setName("西门吹雪");
		List<User> list = new ArrayList<User>(users.values());
		User user = new User();
		user.setId(2351);
		user.setAge(125);
		user.setName("叶孤城");
		list.add(user);
		System.out.println(userDao.updateCollection(list));
	}
	
	@Test
	public void testDeleteByKey() {
		System.out.println(userDao.deleteByKey(27));
	}
	
	@Test
	public void testDeleteByKeys() {
		Set<Integer> set = new HashSet<Integer>() {
			private static final long serialVersionUID = 1L;
			{
				add(1);
			}
		};
		System.out.println(userDao.deleteByKeys(set));
	}
	
	@Test
	public void testQueryList() {
		Query<?> query = new Query<>().eq("age", 10).eq("name", "test").in("id", 70, 80, 90);
		List<User> list = userDao.queryList(query);
		for (User user: list)
			System.out.println(user.getId() + " " + user.getAge() + " " + user.getName());
	}
	
	@Test
	public void testQuery() {
		Query<?> query = new Query<>().eq("age", 10).eq("name", "test").in("id", 70, 80, 90);
		Map<Integer, User> map = userDao.query(query);
		for (User user: map.values())
			System.out.println(user.getId() + " " + user.getAge() + " " + user.getName());
	}
	
	@Test
	public void testQueryUnique() {
		Query<?> query = new Query<>().eq("age", 10).eq("name", "test").in("id", 70, 80, 90).limit(1);
		User user = userDao.queryUnique(query);
		System.out.println(user.getId() + " " + user.getAge() + " " + user.getName());
	}
}
