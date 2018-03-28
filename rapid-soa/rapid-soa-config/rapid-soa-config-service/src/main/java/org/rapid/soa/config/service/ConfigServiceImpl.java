package org.rapid.soa.config.service;

import javax.annotation.Resource;

import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.model.option.Option;
import org.rapid.soa.config.api.ConfigService;
import org.rapid.soa.config.bean.entity.CfgGlobal;
import org.rapid.soa.config.bean.model.Configs;
import org.rapid.soa.config.bean.model.query.ConfigQuery;
import org.rapid.soa.config.mybatis.dao.CfgGlobalDao;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private CfgGlobalDao cfgGlobalDao;

	@Override
	public Configs configs(int type) {
		Configs configs = new Configs();
		ConfigQuery query = new ConfigQuery().type(type);
		configs.setGlobals(cfgGlobalDao.query(query));
		return configs;
	}

	@Override
	public <T> T config(Option<T> option) {
		CfgGlobal config = cfgGlobalDao.getByKey(option.getKey());
		return null == config ? option.getDefaultValue() : RapidConfiguration.CONVERSION_SERVICE.convert(config.getValue(), option.getClazz());
	}
}
