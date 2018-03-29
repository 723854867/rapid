package org.rapid.soa.config.mybatis.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.rapid.dao.db.DBDao;
import org.rapid.soa.config.bean.entity.CfgModular;

public interface CfgModularDao extends DBDao<Integer, CfgModular> {

	Set<Integer> modulars(@Param("modulars" )List<CfgModular> modulars);
}
