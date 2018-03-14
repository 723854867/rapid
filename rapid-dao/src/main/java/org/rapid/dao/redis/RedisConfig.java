package org.rapid.dao.redis;

import java.util.Set;

import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.util.CollectionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

@Configuration
@Conditional(RedisCondition.class)
public class RedisConfig {
	
	@Bean(name = "jedisPool")
	public Pool<Jedis> jedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		// 连接耗尽时是否阻塞：false-直接抛异常，true-阻塞直到超时，默认 true
		config.setBlockWhenExhausted(RapidConfiguration.get(CoreConsts.REDIS_BLOCK_WHEN_EXHAUSTED, true));
		// 设置注册策略类名：默认 DefaultEvictionPolicy(当连接超过最大空闲时间，或连接数超过最大空闲连接数时逐出)
		config.setEvictionPolicyClassName(RapidConfiguration.get(CoreConsts.REDIS_EVICTION_POLICY_CLASS, true));
//		config.setFairness(fairness);
		// 是否启用pool的jmx管理功能, 默认true
		config.setJmxEnabled(RapidConfiguration.get(CoreConsts.REDIS_JMX_ENABLED, true));
//		config.setJmxNameBase(jmxNameBase);
//		config.setJmxNamePrefix("");
		// 是否启用后进先出  - last in first out, 默认true
		config.setLifo(RapidConfiguration.get(CoreConsts.REDIS_LIFO, true));
		// 最大空闲连接数, 默认8个
		config.setMaxIdle(RapidConfiguration.get(CoreConsts.REDIS_MAX_IDLE, true));
		// 最小空闲连接数, 默认0
		config.setMinIdle(RapidConfiguration.get(CoreConsts.REDIS_MIN_IDLE, true));
		// 最大连接数, 默认8个
		config.setMaxTotal(RapidConfiguration.get(CoreConsts.REDIS_MAX_TOTAL, true));
		// 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间, 默认-1
		config.setMaxWaitMillis((long)RapidConfiguration.get(CoreConsts.REDIS_MAX_WAIT_MILLIS, true));
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		config.setMinEvictableIdleTimeMillis((long)RapidConfiguration.get(CoreConsts.REDIS_MIN_EVICTABLE_IDLE_TIME_MILLIS, true));
		// 每次逐出检查时逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		config.setNumTestsPerEvictionRun(RapidConfiguration.get(CoreConsts.REDIS_NUM_TESTS_PER_EVICTION_RUN, true));
		// 对象空闲多久后逐出, 当空闲时间>该值且空闲连接>最大空闲数时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
		config.setSoftMinEvictableIdleTimeMillis((long)RapidConfiguration.get(CoreConsts.REDIS_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS, true));
		// 在获取连接的时候检查有效性, 默认false
		config.setTestOnBorrow(RapidConfiguration.get(CoreConsts.REDIS_TEST_ON_BORROW, true));
		config.setTestOnCreate(RapidConfiguration.get(CoreConsts.REDIS_TEST_ON_CREATE, true));
		config.setTestOnReturn(RapidConfiguration.get(CoreConsts.REDIS_TEST_ON_RETURN, true));
		// 在空闲时检查有效性, 默认false
		config.setTestWhileIdle(RapidConfiguration.get(CoreConsts.REDIS_TEST_WHILE_IDLE, true));
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		config.setTimeBetweenEvictionRunsMillis((long)RapidConfiguration.get(CoreConsts.REDIS_TIME_BETWEEN_EVICTION_RUNS_MILLIS, true));
		
		String poolName = RapidConfiguration.get(CoreConsts.REDIS_POOL, true);
		if (poolName.equals(ShardedJedisPool.class.getName())) {
			return null;
		} else if (poolName.equals(JedisSentinelPool.class.getName())) {
			String sentinels = RapidConfiguration.get(CoreConsts.REDIS_HOST, true);
			Set<String> set = CollectionUtil.splitIntoStringSet(sentinels, ";");
			return new JedisSentinelPool(RapidConfiguration.get(CoreConsts.REDIS_MASTER, true), set, config, 
					RapidConfiguration.get(CoreConsts.REDIS_CONN_TIMEOUT, true), 
					RapidConfiguration.get(CoreConsts.REDIS_PASSWORD, true));
		} else {
			return new JedisPool(config, RapidConfiguration.get(CoreConsts.REDIS_HOST, true), 
					RapidConfiguration.get(CoreConsts.REDIS_PORT, true), 
					RapidConfiguration.get(CoreConsts.REDIS_CONN_TIMEOUT, true), 
					RapidConfiguration.get(CoreConsts.REDIS_PASSWORD, true),
					RapidConfiguration.get(CoreConsts.REDIS_DATABASE, true));
		}
	}
}
