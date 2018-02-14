package org.rapid.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.rapid.core.Assert;
import org.rapid.core.bean.model.code.ICode;
import org.rapid.dao.redis.Redis;
import org.rapid.dao.redis.RedisLock;

public class RedisTest extends DaoTest {

	@Resource
	private Redis redis;
	@Resource
	private RedisLock redisLock;
	
	@Test
	public void testCaptchaObtain() { 
		ICode code = redis.captchaObtain("test", "count", "6231", 60000, 3, 300000, 10000);
		System.out.println(code);
	}
	
	@Test
	public void testDelIfEqual() {
		redis.set("test", "value");
		boolean success = redis.delIfEquals("test", "value");
		Assert.isTrue(success);
		redis.set("test", "value1");
		success = redis.delIfEquals("test", "value");
		Assert.isTrue(!success);
	}
	
	@Test
	public void testTryLock() {
		String lock = "testLock";
		String lockId = redisLock.tryLock(lock);
		Assert.notNull(lockId);
		Assert.isTrue(redisLock.releaseLock(lock, lockId));
	}
	
	@Test
	public void testMultiLock() {
		String lockId = redisLock.tryLock("lock1", "lock2", "lock3");
		Assert.notNull(lockId);
		Assert.isTrue(redisLock.releaseLocks(lockId, "lock1", "lock2", "lock3"));
	}
}
