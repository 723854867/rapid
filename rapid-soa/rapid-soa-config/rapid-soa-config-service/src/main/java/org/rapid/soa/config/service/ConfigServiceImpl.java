package org.rapid.soa.config.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.model.option.Option;
import org.rapid.core.serialize.SerializeUtil;
import org.rapid.soa.config.api.ConfigService;
import org.rapid.soa.config.bean.entity.CfgGlobal;
import org.rapid.soa.config.mybatis.dao.CfgGlobalDao;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private CfgGlobalDao cfgGlobalDao;

	@Override
	public <T> T config(Class<T> clazz) {
		List<CfgGlobal> configs = cfgGlobalDao.getAllList();
		Map<String, String> params = new HashMap<String, String>();
		configs.forEach(item -> params.put(item.getKey(), item.getValue()));
		String json = SerializeUtil.GSON.toJson(params);
		return SerializeUtil.GSON.fromJson(json, clazz);
	}

	@Override
	public <T> T config(Option<T> option) {
		CfgGlobal config = cfgGlobalDao.getByKey(option.getKey());
		return null == config ? option.getDefaultValue() : RapidConfiguration.CONVERSION_SERVICE.convert(config.getValue(), option.getClazz());
	}
}
