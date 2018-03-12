package org.rapid.dao.db.mybatis;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.dao.db.DBConfig;
import org.rapid.dao.db.conditions.MybatisCondition;
import org.rapid.util.CollectionUtil;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

@Configuration
@EnableTransactionManagement
@Conditional(MybatisCondition.class)
public class MybatisConfig extends DBConfig {

	@Bean("sqlSessionFactory")
	public SqlSessionFactoryBean sessionFactory() {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(datasource());
		String mapperLocation = RapidConfiguration.get(CoreConsts.DB_MYBATIS_MAPPER_LOCATION, false);
		if (StringUtil.hasText(mapperLocation))
			factory.setMapperLocations(RapidConfiguration.getResources(mapperLocation));
		factory.setTypeAliasesPackage(RapidConfiguration.get(CoreConsts.DB_MYBATIS_TYPE_ALIASES_PACKAGE, false));
		Set<Interceptor> interceptors = new HashSet<Interceptor>();
		boolean page = RapidConfiguration.get(CoreConsts.DB_MYBATIS_PAGE, false); 
		if (page) {
			PageInterceptor interceptor = new PageInterceptor();
			interceptor.setProperties(new Properties());
			interceptors.add(interceptor);
		}
		if (!CollectionUtil.isEmpty(interceptors))
			factory.setPlugins(interceptors.toArray(new Interceptor[] {}));
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setCacheEnabled(RapidConfiguration.get(CoreConsts.DB_SESSION_CACHE_ENABLED, false));
		configuration.setMapUnderscoreToCamelCase(RapidConfiguration.get(CoreConsts.DB_SESSION_CAMEL_2_UNDERLINE, false));
		factory.setConfiguration(configuration);
		return factory;
	}
	
	@Bean("sqlSession")
	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
