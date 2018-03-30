package org.rapid.soa.config.mybatis.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.rapid.dao.db.DBDao;
import org.rapid.soa.config.bean.entity.CfgAuthority;

public interface CfgAuthorityDao extends DBDao<Integer, CfgAuthority> {

	@Delete("DELETE FROM cfg_authority WHERE type=#{type} AND tid=#{tid}")
	int deleteByTypeAndTid(@Param("type") int type, @Param("tid") int tid);
	
	@Delete("DELETE FROM cfg_authority WHERE type=#{type} AND auth_id=#{authId}")
	int deleteByTypeAndAuthId(@Param("type") int type, @Param("auth_id") int authId);
	
	int deleteRoleModulars(@Param("collection") Set<Integer> ids);
	
	int deleteModularGateways(@Param("collection") Set<Integer> ids);
}
