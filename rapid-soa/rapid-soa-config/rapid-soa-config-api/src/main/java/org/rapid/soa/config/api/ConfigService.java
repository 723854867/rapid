package org.rapid.soa.config.api;

import org.rapid.core.bean.model.option.Option;
import org.rapid.soa.config.bean.model.Configs;

public interface ConfigService {

	Configs configs(int type);
	
	<T> T config(Option<T> option);
}
