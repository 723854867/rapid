package org.rapid.dao.db.mybatis;

import java.util.Properties;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.rapid.dao.db.DBDao;
import org.rapid.util.StringUtil;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class DaoScannerConfigurer extends MapperScannerConfigurer {

	private DaoAccessor daoAccessor = new DaoAccessor();

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
		super.postProcessBeanDefinitionRegistry(registry);
		daoAccessor.registerDao(DBDao.class);
		String[] names = registry.getBeanDefinitionNames();
		GenericBeanDefinition definition;
		for (String name : names) {
			BeanDefinition beanDefinition = registry.getBeanDefinition(name);
			if (beanDefinition instanceof GenericBeanDefinition) {
				definition = (GenericBeanDefinition) beanDefinition;
				String clazz = definition.getBeanClassName();
				if (StringUtil.hasText(clazz) && clazz.equals("org.mybatis.spring.mapper.MapperFactoryBean")) {
					definition.setBeanClass(DaoFactoryBean.class);
					definition.getPropertyValues().add("daoAccessor", this.daoAccessor);
				}
			}
		}
	}

	/**
	 * 属性注入
	 */
	public void setProperties(Properties properties) {
		daoAccessor.setProperties(properties);
	}
}
