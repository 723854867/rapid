package org.rapid.dao.db.provider;

import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class BaseDeleteProvider extends tk.mybatis.mapper.provider.base.BaseDeleteProvider {

	public BaseDeleteProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

}
