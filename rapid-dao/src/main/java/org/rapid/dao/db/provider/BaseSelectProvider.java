package org.rapid.dao.db.provider;

import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class BaseSelectProvider extends tk.mybatis.mapper.provider.base.BaseSelectProvider {

	public BaseSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

}
