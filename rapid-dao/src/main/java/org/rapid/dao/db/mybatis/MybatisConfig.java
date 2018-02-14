package org.rapid.dao.db.mybatis;

import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.rapid.dao.db.DBConfig;
import org.rapid.dao.db.conditions.MybatisCondition;
import org.rapid.util.CollectionUtil;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

@Configuration
@Conditional(MybatisCondition.class)
@PropertySource("classpath:conf/db.properties")
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class MybatisConfig extends DBConfig {

	@Bean("sqlSessionFactory")
	public SqlSessionFactoryBean sessionFactory() throws Exception{
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(datasource());
		String mapperLocation = getProperty("db.mybatis.mapperLocation", null, String.class);
		if (StringUtil.hasText(mapperLocation))
			factory.setMapperLocations(resourceResolver.getResources(mapperLocation));
		factory.setTypeAliasesPackage(getProperty("db.mybatis.typeAliasesPackage", null, String.class));
		Set<Interceptor> interceptors = new HashSet<Interceptor>();
		boolean page = getProperty("db.mybatis.page", false, boolean.class); 
		if (page)
			interceptors.add(new PageInterceptor());
		if (!CollectionUtil.isEmpty(interceptors))
			factory.setPlugins(interceptors.toArray(new Interceptor[] {}));
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setCacheEnabled(getProperty("db.session.cacheEnabled", false, boolean.class));
		configuration.setMapUnderscoreToCamelCase(getProperty("db.session.camel2underline", true, boolean.class));
		factory.setConfiguration(configuration);
		return factory;
	}
	
	@Bean("sqlSession")
	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
