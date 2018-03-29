package org.rapid.soa.config.bean.model;

import java.io.Serializable;
import java.util.Set;

import org.rapid.soa.config.bean.entity.CfgGateway;

public class Gateway implements Serializable {

	private static final long serialVersionUID = -6164056188684361908L;

	// 网关配置
	private CfgGateway gateway;
	// 如果网关开启权限检测则需要判断的有用权限模块的id
	private Set<Integer> modulars;
	
	public CfgGateway getGateway() {
		return gateway;
	}
	
	public void setGateway(CfgGateway gateway) {
		this.gateway = gateway;
	}
	
	public Set<Integer> getModulars() {
		return modulars;
	}
	
	public void setModulars(Set<Integer> modulars) {
		this.modulars = modulars;
	}
}
