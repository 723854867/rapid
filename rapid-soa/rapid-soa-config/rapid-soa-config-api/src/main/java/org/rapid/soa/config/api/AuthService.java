package org.rapid.soa.config.api;

import java.util.List;
import java.util.Set;

import org.rapid.soa.config.bean.entity.CfgGateway;
import org.rapid.soa.config.bean.info.ModularInfo;
import org.rapid.soa.config.bean.request.CreateAuthorityRequest;
import org.rapid.soa.config.bean.request.CreateGatewayRequest;
import org.rapid.soa.config.bean.request.CreateModularRequest;
import org.rapid.soa.config.bean.request.CreateRoleRequest;
import org.rapid.soa.config.bean.request.ModifyModularRequest;
import org.rapid.soa.config.bean.request.ModifyRoleRequest;
import org.rapid.soa.core.bean.request.SoaIdRequest;

public interface AuthService {
	
	// 获取模块结构列表
	List<ModularInfo> modulars();
	
	// 获取网关配置
	CfgGateway gateway(String path);
	
	// 删除角色
	void deleteRole(SoaIdRequest request);
	
	// 删除网关
	void deleteGateway(SoaIdRequest request);
	
	// 删除模块
	void deleteModular(SoaIdRequest request);
	
	// 删除权限
	void deleteAuthority(SoaIdRequest request);
	
	// 新建角色
	int createRole(CreateRoleRequest request);
	
	// 修改角色
	void modifyRole(ModifyRoleRequest request);
	
	// 新建网关
	int createGateway(CreateGatewayRequest request);
	
	// 新建模块
	int createModular(CreateModularRequest request);
	
	// 修改模块
	void modifyModular(ModifyModularRequest request);
	
	// 添加权限
	int createAuthority(CreateAuthorityRequest request);
	
	/**
	 *  权限认证
	 *  
	 * @param gateway 网关配置
	 * @param own 用户拥有的权限模块id
	 */
	void auth(CfgGateway gateway, Set<Integer> own);
}
