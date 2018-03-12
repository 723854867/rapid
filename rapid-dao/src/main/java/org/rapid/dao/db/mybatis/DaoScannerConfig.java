package org.rapid.dao.db.mybatis;

import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.dao.db.conditions.MybatisCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(MybatisCondition.class)
public class DaoScannerConfig {

	@Bean
	public DaoScannerConfigurer mapperScannerConfigurer() throws Exception {
		DaoScannerConfigurer scanner = new DaoScannerConfigurer();
		String value = RapidConfiguration.get(CoreConsts.DB_MYBATIS_BASE_PACKAGE, false);
		if (null != value)
			scanner.setBasePackage(value);
		return scanner;
	}
}
