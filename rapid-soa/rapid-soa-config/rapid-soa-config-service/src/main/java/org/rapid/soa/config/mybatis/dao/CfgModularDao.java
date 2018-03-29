package org.rapid.soa.config.mybatis.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.rapid.dao.db.DBDao;
import org.rapid.soa.config.bean.entity.CfgModular;

public interface CfgModularDao extends DBDao<Integer, CfgModular> {

	Set<Integer> modulars(@Param("modulars" ) List<CfgModular> modulars);
	
	@Update("UPDATE cfg_modular SET left = CASE WHEN left > #{value} THEN left + 2 ELSE left END, right = CASE WHEN right >= #(value) THEN right + 2 ELSE right END WHERE trunk=#{trunk}")
	int insertUpdate(@Param("trunk") long trunk, @Param("value") int value);
}
