package org.rapid.dao.db;

import javax.sql.DataSource;

import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.model.code.Code;
import org.rapid.dao.db.conditions.DataSourceCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public abstract class DBConfig extends RapidConfiguration {
	
	@Bean(name = "dataSource", destroyMethod = "close")
	@Conditional(DataSourceCondition.class)
	public DataSource datasource() {
		String datasource = RapidConfiguration.get(CoreConsts.DB_DATASOURCE_CLASS, true);
		if (datasource.equals("com.zaxxer.hikari.HikariDataSource")) 
			return _hikariCP();
		else 
			throw new BizException(Code.SYS_ERROR, "Unsupport dataSource type: " + datasource);
	}
	
	private DataSource _hikariCP() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_DRIVER_CLASS, true));
		config.setJdbcUrl(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_JDBC, true));
		config.setUsername(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_USERNAME, true));
		config.setPassword(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_PASSWORD, true));
		// 连接池中允许的最大连接数。常见的错误是设置一个太大的值，连接数多反而性能下降。参考计算公式是：connections = ((cpu * 2) + 硬盘数)
		config.setMaximumPoolSize(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_MAX_POOL_SIZE, true));
		// 连接池空闲连接的最小数量，当连接池空闲连接少于minimumIdle，而且总共连接数不大于maximumPoolSize时，HikariCP会尽力补充新的连接。为了性能考虑，不建议设置此值，而是让HikariCP把连接池当做固定大小的处理，
		// 默认minimumIdle与maximumPoolSize一样。当minIdle<0或者minIdle>maxPoolSize,则被重置为maxPoolSize，该值默认为10。
		config.setMinimumIdle(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_MIN_IDLE, true));
		// 一个连接的生命时长（毫秒），超时而且没被使用则被释放。强烈建议设置比数据库超时时长少30秒，（MySQL的wait_timeout参数，show variables like ‘%timeout%’，一般为8小时）
		// 和IdleTimeout的区别是不管连接池链接数量多少，只要一个连接超过该时间没被使用就会被回收
		config.setMaxLifetime(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_MAX_LIFE_TIME, true));
		// 如果idleTimeout+1秒>maxLifetime 且 maxLifetime>0，则会被重置为0。如果idleTimeout=0则表示空闲的连接在连接池中永远不被移除。
		// 只有当minimumIdle小于maximumPoolSize时，这个参数才生效，当空闲连接数超过minimumIdle且空闲时间超过idleTimeout，才会被移除。
		config.setIdleTimeout(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_IDLE_TIMEOUT, true));
		// 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException。 缺省:30秒
		config.setConnectionTimeout(RapidConfiguration.get(CoreConsts.DB_DATASOURCE_CONNECTION_TIMEOUT, true));
		config.addDataSourceProperty("cachePrepStmts", RapidConfiguration.get(CoreConsts.DB_DATASOURCE_CACHE_PREP_STMTS, true));
		config.addDataSourceProperty("prepStmtCacheSize", RapidConfiguration.get(CoreConsts.DB_DATASOURCE_PREP_STMT_CACHE_SIZE, true));
		config.addDataSourceProperty("prepStmtCacheSqlLimit", RapidConfiguration.get(CoreConsts.DB_DATASOURCE_PREP_STMT_CACHE_SQL_LIMIT, true));
		return new HikariDataSource(config);
	}
	
	@Bean
	public PlatformTransactionManager txManager()  {
		return new DataSourceTransactionManager(datasource());
	}
}
