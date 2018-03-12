package org.rapid.core;

import org.rapid.core.bean.enums.Env;
import org.rapid.core.bean.enums.Locale;
import org.rapid.core.bean.model.option.BoolOption;
import org.rapid.core.bean.model.option.IntegerOption;
import org.rapid.core.bean.model.option.Option;
import org.rapid.core.bean.model.option.StrOption;

public interface CoreConsts {

	final Option<Env> RAPID_ENV									= new Option<Env>("rapid.env", Env.LOCAL);
	final StrOption RAPID_RESOURCES_PREFIX 						= new StrOption("rapid.resources.prefix");
	final IntegerOption RAPID_MAX_UPLOAD_SIZE					= new IntegerOption("maxUploadSize", 5242880);
	final IntegerOption RAPID_MAX_IN_MEMORY_SIZE				= new IntegerOption("maxInMemorySize", 51200);
	final Option<Locale> RAPID_LOCALE							= new Option<Locale>("rapid.locale", Locale.ZH_CN);
	final IntegerOption RAPID_MAX_UPLOAD_SIZE_PER_FILE			= new IntegerOption("maxUploadSizePerFile", 1048576);
	
	final StrOption SERIALIZER					= new StrOption("serializer");
	final StrOption DB_ORM_TYPE					= new StrOption("db.ormType");
	final StrOption ACTIVEMQ_TYPE				= new StrOption("activemq.type");
	final BoolOption HTTP_ENABLE				= new BoolOption("http.enable", false);
	final BoolOption REDIS_ENABLE				= new BoolOption("redis.enable", false);
	final BoolOption UPLOAD_ENABLE				= new BoolOption("upload.enable", false);
	final BoolOption ZOOKEEPER_ENABLE			= new BoolOption("zookeeper.enable", false);
	
	// activemq
	final StrOption ACTIVEMQ_USERNAME						= new StrOption("activemq.username");
	final StrOption ACTIVEMQ_PASSWORD						= new StrOption("activemq.password");
	final StrOption ACTIVEMQ_BROKER_URL						= new StrOption("activemq.brokerUrl");
	final BoolOption ACTIVEMQ_DAEMON						= new BoolOption("activemq.daemon", true);
	final BoolOption ACTIVEMQ_TRUST_ALL_PACKAGES			= new BoolOption("activemq.trustAllPackages");
	final BoolOption ACTIVEMQ_USE_ASYNC_SEND				= new BoolOption("activemq.useAsyncSend", false);
	final IntegerOption ACTIVEMQ_MAX_POOL_SIZE				= new IntegerOption("activemq.maxPoolSize", 300);
	final IntegerOption ACTIVEMQ_CORE_POOL_SIZE				= new IntegerOption("activemq.corePoolSize", 150);
	final IntegerOption ACTIVEMQ_MAX_CONNECTIONS			= new IntegerOption("activemq.maxConnections", 100);
	final IntegerOption ACTIVEMQ_RECEIVE_TIMEOUT			= new IntegerOption("activemq.receiveTimeout", 1000);
	final IntegerOption ACTIVEMQ_SESSION_CACHE_SIZE			= new IntegerOption("activemq.sessionCacheSize", 100);
	final IntegerOption ACTIVEMQ_KEEP_ALIVE_SECONDS			= new IntegerOption("activemq.keepAliveSeconds", 120);
	final BoolOption ACTIVEMQ_EXPLICIT_QOS_ENABLED			= new BoolOption("activemq.explicitQosEnabled", true);
	final BoolOption ACTIVEMQ_DELIVERY_PERSISTENT			= new BoolOption("activemq.deliveryPersistent", true);
	final IntegerOption ACTIVEMQ_CONCURRENT_CONSUMERS		= new IntegerOption("activemq.concurrentConsumers", 1);
	
	// zookeeper
	final StrOption ZOOKEEPER_SERVERS						= new StrOption("zoopeeker.servers");
	final StrOption ZOOKEEPER_SERIALIZER					= new StrOption("zookeeper.serializer");
	final IntegerOption ZOOKEEPER_SESSION_TIMEOUT			= new IntegerOption("zookeeper.sessionTimeout");
	final IntegerOption ZOOKEEPER_CONNECTION_TIMEOUT		= new IntegerOption("zookeeper.connectionTimeout");
	
	// db
	final StrOption DB_MYBATIS_BASE_PACKAGE							= new StrOption("db.mybatis.basePackage");
	final BoolOption DB_MYBATIS_PAGE								= new BoolOption("db.mybatis.page", false);
	final StrOption DB_MYBATIS_MAPPER_LOCATION						= new StrOption("db.mybatis.mapperLocation");
	final StrOption DB_MYBATIS_TYPE_ALIASES_PACKAGE					= new StrOption("db.mybatis.typeAliasesPackage");
	final BoolOption DB_SESSION_CACHE_ENABLED						= new BoolOption("db.session.cacheEnabled", false);
	final BoolOption DB_SESSION_CAMEL_2_UNDERLINE					= new BoolOption("db.session.camel2underline", true);
	
	// datasource
	final StrOption DB_DATASOURCE_CLASS								= new StrOption("db.datasource.class");
	final StrOption DB_DATASOURCE_JDBC								= new StrOption("db.datasource.jdbc");
	final StrOption DB_DATASOURCE_USERNAME							= new StrOption("db.datasource.username");
	final StrOption DB_DATASOURCE_PASSWORD							= new StrOption("db.datasource.password");
	final StrOption DB_DATASOURCE_DRIVER_CLASS						= new StrOption("db.datasource.driverClass");
	final IntegerOption DB_DATASOURCE_MAX_POOL_SIZE					= new IntegerOption("db.datasource.maxPoolSize", 10);
	final IntegerOption DB_DATASOURCE_MIN_IDLE						= new IntegerOption("db.datasource.minIdle", 10);
	final IntegerOption DB_DATASOURCE_MAX_LIFE_TIME					= new IntegerOption("db.datasource.maxLifeTime", 1800000);
	final IntegerOption DB_DATASOURCE_IDLE_TIMEOUT					= new IntegerOption("db.datasource.idleTimeout", 600000);
	final IntegerOption DB_DATASOURCE_CONNECTION_TIMEOUT			= new IntegerOption("db.datasource.connectionTimeout", 30000);
	final BoolOption DB_DATASOURCE_CACHE_PREP_STMTS					= new BoolOption("db.datasource.cachePrepStmts", true);
	final IntegerOption DB_DATASOURCE_PREP_STMT_CACHE_SIZE			= new IntegerOption("db.datasource.prepStmtCacheSize", 250);
	final IntegerOption DB_DATASOURCE_PREP_STMT_CACHE_SQL_LIMIT		= new IntegerOption("db.datasource.prepStmtCacheSqlLimit", 2048);
	
	// redis
	final BoolOption REDIS_BLOCK_WHEN_EXHAUSTED							= new BoolOption("redis.blockWhenExhausted", true);
	final StrOption REDIS_EVICTION_POLICY_CLASS							= new StrOption("redis.evictionPolicyClass", "org.apache.commons.pool2.impl.DefaultEvictionPolicy");
	final BoolOption REDIS_JMX_ENABLED									= new BoolOption("redis.jmxEnabled", true);
	final BoolOption REDIS_LIFO											= new BoolOption("redis.lifo", true);
	final IntegerOption REDIS_MAX_IDLE									= new IntegerOption("redis.maxIdle", 8);
	final IntegerOption REDIS_MIN_IDLE									= new IntegerOption("redis.minIdle", 0);
	final IntegerOption REDIS_MAX_TOTAL									= new IntegerOption("redis.maxTotal", 8);
	final IntegerOption REDIS_MAX_WAIT_MILLIS							= new IntegerOption("redis.maxWaitMillis", -1);
	final IntegerOption REDIS_MIN_EVICTABLE_IDLE_TIME_MILLIS			= new IntegerOption("redis.minEvictableIdleTimeMillis", 1800000);
	final IntegerOption REDIS_NUM_TESTS_PER_EVICTION_RUN				= new IntegerOption("redis.numTestsPerEvictionRun", 3);
	final IntegerOption REDIS_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS		= new IntegerOption("redis.softMinEvictableIdleTimeMillis", 1800000);
	final BoolOption REDIS_TEST_ON_BORROW								= new BoolOption("redis.testOnBorrow", false);
	final BoolOption REDIS_TEST_ON_CREATE								= new BoolOption("redis.testOnCreate", false);
	final BoolOption REDIS_TEST_ON_RETURN								= new BoolOption("redis.testOnReturn", false);
	final BoolOption REDIS_TEST_WHILE_IDLE								= new BoolOption("redis.testWhileIdle", false);
	final IntegerOption REDIS_TIME_BETWEEN_EVICTION_RUNS_MILLIS			= new IntegerOption("redis.timeBetweenEvictionRunsMillis", -1);
	final StrOption REDIS_POOL											= new StrOption("redis.pool", "redis.clients.jedis.JedisPool");
	final IntegerOption REDIS_CONN_TIMEOUT								= new IntegerOption("redis.connTimeout", 3000);
	final StrOption REDIS_PASSWORD										= new StrOption("redis.password");
	final StrOption REDIS_HOST											= new StrOption("redis.host");
	final IntegerOption REDIS_PORT										= new IntegerOption("redis.port", 6379);
	final IntegerOption REDIS_DATABASE									= new IntegerOption("redis.database", 0);
	final StrOption REDIS_MASTER										= new StrOption("redis.masterName");
	
	// mongo
	final StrOption MONGO_DB					= new StrOption("mongo.db");
	final StrOption MONGO_HOST					= new StrOption("mongo.host");
	final BoolOption MONGO_ENABLE				= new BoolOption("mongo.enable", false);
	
	// dubbo
	final StrOption DUBBO_APP_NAME					= new StrOption("dubbo.app.name");
	final StrOption DUBBO_APP_OWNER					= new StrOption("dubbo.app.owner");
	final StrOption DUBBO_APP_ENVIRONMENT			= new StrOption("dubbo.app.environment");
	final StrOption DUBBO_APP_ORGANZATION			= new StrOption("dubbo.app.organzation");
	final StrOption DUBBO_REGISTRY_ID				= new StrOption("dubbo.registry.id");
	final StrOption DUBBO_REGISTRY_PROTOCOL			= new StrOption("dubbo.registry.protocol");
	final IntegerOption DUBBO_REGISTRY_PORT			= new IntegerOption("dubbo.registry.port");
	final StrOption DUBBO_REGISTRY_USERNAME			= new StrOption("dubbo.registry.username");
	final StrOption DUBBO_REGISTRY_PASSWORD			= new StrOption("dubbo.registry.password");
	final StrOption DUBBO_REGISTRY_TRANSPORTER		= new StrOption("dubbo.registry.transporter");
	final IntegerOption DUBBO_REGISTRY_TIMEOUT		= new IntegerOption("dubbo.registry.timeout");
	final IntegerOption DUBBO_REGISTRY_SESSION		= new IntegerOption("dubbo.registry.session");
	final StrOption DUBBO_REGISTRY_FILE				= new StrOption("dubbo.registry.file");
	final StrOption DUBBO_REGISTRY_ADDRESS			= new StrOption("dubbo.registry.address");
	final IntegerOption DUBBO_PROTOCOL_PORT			= new IntegerOption("dubbo.protocol.port", -1);
	final StrOption DUBBO_PROTOCOL_TYPE				= new StrOption("dubbo.protocol.type", "dubbo");
	final StrOption DUBBO_PROTOCOL_THREADPOOL		= new StrOption("dubbo.protocol.threadpool", "fixed");
	final IntegerOption DUBBO_PROTOCOL_THREADS		= new IntegerOption("dubbo.protocol.threads", 100);
	final IntegerOption DUBBO_PROTOCOL_IOTHREADS	= new IntegerOption("dubbo.protocol.iothreads", Runtime.getRuntime().availableProcessors() + 1);
	final IntegerOption DUBBO_PROTOCOL_ACCEPTS		= new IntegerOption("dubbo.protocol.accepts", 0);
	final IntegerOption DUBBO_PROTOCOL_PAYLOAD		= new IntegerOption("dubbo.protocol.payload", 88388608);
	final IntegerOption DUBBO_PROTOCOL_TIMEOUT		= new IntegerOption("dubbo.protocol.timeout", 3000);
}
