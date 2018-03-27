package org.rapid.soa.config.api;

import org.rapid.core.bean.model.option.Option;

public interface ConfigService {

	/**
	 * 将配置映射为java对象，需要注意的是config必须引入了clazz，否则会抛class not found
	 */
	<T> T config(Class<T> clazz);
	
	<T> T config(Option<T> option);
}
