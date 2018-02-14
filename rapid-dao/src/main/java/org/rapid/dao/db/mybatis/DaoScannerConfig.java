package org.rapid.dao.db.mybatis;

import java.util.Properties;

import org.rapid.core.ResourceLoader;
import org.rapid.dao.db.conditions.MybatisCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(MybatisCondition.class)
public class DaoScannerConfig extends ResourceLoader {

	@Bean
	public DaoScannerConfigurer mapperScannerConfigurer() throws Exception {
		DaoScannerConfigurer scanner = new DaoScannerConfigurer();
		Properties properties = loadProperties("classpath:conf/db.properties");
		scanner.setBasePackage(properties.getProperty("db.mybatis.basePackage"));
		return scanner;
	}
}
