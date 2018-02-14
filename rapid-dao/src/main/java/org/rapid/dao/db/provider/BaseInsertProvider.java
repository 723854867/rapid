package org.rapid.dao.db.provider;

import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class BaseInsertProvider extends tk.mybatis.mapper.provider.base.BaseInsertProvider {

	public BaseInsertProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

}
