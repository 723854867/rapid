package org.rapid.soa.config.mybatis.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.rapid.dao.db.DBDao;
import org.rapid.soa.config.bean.entity.CfgModular;

public interface CfgModularDao extends DBDao<Integer, CfgModular> {
	
	@Select("SELECT id FROM cfg_modular WHERE  trunk = #{trunk} AND left >= #{left} AND right <= #{right}")
	Set<Integer> tree(int id);
	
	@Delete("DELETE FROM cfg_modular WHERE trunk = #{trunk} AND left >= #{left} AND right <= #{right}")
	int deleteNode(CfgModular modular);
	
	@Update("UPDATE cfg_modular SET left = CASE WHEN left > #{right} THEN left - #{delt} ELSE left END, right = CASE WHEN right > #{right} THEN right - #{delt} ELSE right END WHERE trunk=#{trunk}")
	int deleteUpdate(CfgModular modular, int delt);
	
	Set<Integer> modulars(@Param("modulars" ) List<CfgModular> modulars);
	
	@Update("UPDATE cfg_modular SET left = CASE WHEN left > #{value} THEN left + 2 ELSE left END, right = CASE WHEN right >= #(value) THEN right + 2 ELSE right END WHERE trunk=#{trunk}")
	int insertUpdate(@Param("trunk") long trunk, @Param("value") int value);
}
