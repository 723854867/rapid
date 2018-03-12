package org.rapid.dao;

public interface DaoConsts {

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
