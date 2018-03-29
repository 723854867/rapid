package org.rapid.soa.config.bean.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

/**
 * <pre>>
 * 网关和模块映射
 * modularId 和   gatewayId 做唯一索引
 * </pre>
 * 
 * @author lynn
 */
public class CfgAuthority implements Identifiable<Integer> {

	private static final long serialVersionUID = -1449282195019172095L;

	@Id
	@GeneratedValue
	private int id;
	private int gatewayId;
	private int modularId;
	private int created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGatewayId() {
		return gatewayId;
	}
	
	public void setGatewayId(int gatewayId) {
		this.gatewayId = gatewayId;
	}

	public int getModularId() {
		return modularId;
	}
	
	public void setModularId(int modularId) {
		this.modularId = modularId;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public Integer identity() {
		return this.id;
	}
}
