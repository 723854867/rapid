package org.rapid.dao;

import org.rapid.core.bean.model.option.BoolOption;
import org.rapid.core.bean.model.option.StrOption;

public interface DaoConsts {

	final StrOption DB_ORM_TYPE					= new StrOption("db.ormType");
	final BoolOption REDIS_ENABLE				= new BoolOption("redis.enable", false);
	final BoolOption MONGO_ENABLE				= new BoolOption("mongo.enable", false);
	
	interface RedisConsts {
		
		final String OK							= "OK";

		enum NXXX {
			NX,
			XX;
		}
		
		enum EXPX {
			EX,
			PX;
		}
	}
}
