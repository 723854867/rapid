package org.rapid.soa.config.api;

import java.util.List;
import java.util.Set;

import org.rapid.soa.config.bean.entity.CfgGateway;
import org.rapid.soa.config.bean.info.ModularInfo;

public interface GatewayService {

	CfgGateway gateway(String path);
	
	// 获取模块结构列表
	List<ModularInfo> modulars();
	
	/**
	 *  权限认证
	 *  
	 * @param gateway 网关配置
	 * @param own 用户拥有的权限模块id
	 */
	void auth(CfgGateway gateway, Set<Integer> own);
}
