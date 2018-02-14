package org.rapid.dao.db.provider;

import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class BaseUpdateProvider extends tk.mybatis.mapper.provider.base.BaseUpdateProvider {

	public BaseUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}
}
