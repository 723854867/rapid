package org.rapid.soa.user.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.rapid.dao.db.DBDao;
import org.rapid.soa.user.bean.entity.UserRole;

public interface UserRoleDao extends DBDao<Long, UserRole> {
	
	@Delete("DELETE FROM user_role WHERE trunk = #{trunk} AND left >= #{left} AND right <= #{right}")
	void unauth(UserRole role);
	
	@Select("SELECT COUNT(*) FROM user_role WHERE trunk = #{trunk} AND left < #{left} AND right > #{right} AND uid = #{uid}")
	int parent(@Param("trunk") long trunk, @Param("left") long left, @Param("right") long right, @Param("uid") long uid);

	@Update("UPDATE user_role SET left = CASE WHEN left > #{right} THEN left - #{delt} ELSE left END, right = CASE WHEN right > #{right} THEN right - #{delt} ELSE right END WHERE trunk=#{trunk}")
	int deleteUpdate(UserRole role, long delt);
	
	@Update("UPDATE user_role SET left = CASE WHEN left > #{value} THEN left + 2 ELSE left END, right = CASE WHEN right >= #(value) THEN right + 2 ELSE right END WHERE trunk=#{trunk}")
	int insertUpdate(@Param("trunk") long trunk, @Param("value") long value);
}
