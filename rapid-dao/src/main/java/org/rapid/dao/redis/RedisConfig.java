package org.rapid.dao.redis;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.pool2.impl.DefaultEvictionPolicy;
import org.rapid.util.CollectionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

@Configuration
@PropertySource("classpath:conf/redis.properties")
@Conditional(RedisCondition.class)
public class RedisConfig {
	
	@Resource
	private Environment environment;

	@Bean(name = "jedisPool")
	public Pool<Jedis> jedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		// 连接耗尽时是否阻塞：false-直接抛异常，true-阻塞直到超时，默认 true
		config.setBlockWhenExhausted(environment.getProperty("redis.blockWhenExhausted", boolean.class, true));
		// 设置注册策略类名：默认 DefaultEvictionPolicy(当连接超过最大空闲时间，或连接数超过最大空闲连接数时逐出)
		config.setEvictionPolicyClassName(environment.getProperty("redis.evictionPolicyClassName", String.class, DefaultEvictionPolicy.class.getName()));
//		config.setFairness(fairness);
		// 是否启用pool的jmx管理功能, 默认true
		config.setJmxEnabled(environment.getProperty("redis.jmxEnabled", boolean.class, true));
//		config.setJmxNameBase(jmxNameBase);
//		config.setJmxNamePrefix("");
		// 是否启用后进先出  - last in first out, 默认true
		config.setLifo(environment.getProperty("redis.lifo", boolean.class, true));
		// 最大空闲连接数, 默认8个
		config.setMaxIdle(environment.getProperty("redis.maxIdle", int.class, 8));
		// 最小空闲连接数, 默认0
		config.setMinIdle(environment.getProperty("redis.minIdle", int.class, 0));
		// 最大连接数, 默认8个
		config.setMaxTotal(environment.getProperty("redis.maxTotal", int.class, 8));
		// 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间, 默认-1
		config.setMaxWaitMillis(environment.getProperty("redis.maxWaitMillis", int.class, -1));
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		config.setMinEvictableIdleTimeMillis(environment.getProperty("redis.minEvictableIdleTimeMillis", int.class, 1800000));
		// 每次逐出检查时逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		config.setNumTestsPerEvictionRun(environment.getProperty("redis.numTestsPerEvictionRun", int.class, 3));
		// 对象空闲多久后逐出, 当空闲时间>该值且空闲连接>最大空闲数时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
		config.setSoftMinEvictableIdleTimeMillis(environment.getProperty("redis.softMinEvictableIdleTimeMillis", int.class, 1800000));
		// 在获取连接的时候检查有效性, 默认false
		config.setTestOnBorrow(environment.getProperty("redis.testOnBorrow", boolean.class, false));
		config.setTestOnCreate(environment.getProperty("redis.testOnCreate", boolean.class, false));
		config.setTestOnReturn(environment.getProperty("redis.testOnReturn", boolean.class, false));
		// 在空闲时检查有效性, 默认false
		config.setTestWhileIdle(environment.getProperty("redis.testWhileIdle", boolean.class, false));
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		config.setTimeBetweenEvictionRunsMillis(environment.getProperty("redis.timeBetweenEvictionRunsMillis", int.class, -1));
		
		String poolName = environment.getProperty("redis.pool", String.class, JedisPool.class.getName());
		if (poolName.equals(ShardedJedisPool.class.getName())) {
			return null;
		} else if (poolName.equals(JedisSentinelPool.class.getName())) {
			String sentinels = environment.getProperty("redis.host");
			Set<String> set = CollectionUtil.splitIntoStringSet(sentinels, ";");
			return new JedisSentinelPool(environment.getProperty("redis.masterName"), set, config, 
					environment.getProperty("redis.connTimeout", int.class, 3000), 
					environment.getProperty("redis.password", String.class));
		} else {
			return new JedisPool(config, environment.getProperty("redis.host"), 
					environment.getProperty("redis.port", int.class, 6379), 
					environment.getProperty("redis.connTimeout", int.class, 3000), 
					environment.getProperty("redis.password", String.class),
					environment.getProperty("redis.database", int.class, 0));
		}
	}
}
