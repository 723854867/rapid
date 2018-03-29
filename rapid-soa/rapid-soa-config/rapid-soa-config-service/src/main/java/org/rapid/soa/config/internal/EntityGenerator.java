package org.rapid.soa.config.internal;

import org.rapid.core.IDWorker;
import org.rapid.soa.config.bean.entity.CfgAuthority;
import org.rapid.soa.config.bean.entity.CfgGateway;
import org.rapid.soa.config.bean.entity.CfgModular;
import org.rapid.soa.config.bean.entity.CfgRole;
import org.rapid.soa.config.bean.request.CreateAuthorityRequest;
import org.rapid.soa.config.bean.request.CreateGatewayRequest;
import org.rapid.soa.config.bean.request.CreateRoleRequest;
import org.rapid.util.DateUtil;

public class EntityGenerator {

	public static final CfgModular newCfgModular(CfgModular parent, String name) {
		CfgModular instance = new CfgModular();
		instance.setName(name);
		instance.setTrunk(null == parent ? IDWorker.INSTANCE.nextId() : parent.getTrunk());
		instance.setLeft(null == parent ? 1 : parent.getRight());
		instance.setRight(instance.getLeft() + 1);
		instance.setLayer(null == parent ? 1 : parent.getLayer() + 1);
		int time = DateUtil.current();
		instance.setCreated(time);
		instance.setUpdated(time);
		return instance;
	}
	
	public static final CfgGateway newCfgGateway(CreateGatewayRequest request) {
		CfgGateway instance = new CfgGateway();
		instance.setAuth(request.isAuth());
		instance.setPath(request.getPath());
		instance.setDesc(request.getDesc());
		instance.setLogin(request.isLogin());
		instance.setSerial(request.isSerial());
		instance.setRecord(request.isRecord());
		instance.setDeviceMod(request.getDeviceMod());
		instance.setLockTimeout(request.getLockTimeout());
		int time = DateUtil.current();
		instance.setCreated(time);
		instance.setUpdated(time);
		return instance;
	}
	
	public static final CfgAuthority newCfgAuthority(CreateAuthorityRequest request) {
		CfgAuthority instance = new CfgAuthority();
		instance.setTid(request.getTid());
		instance.setAuthId(request.getAuthId());
		instance.setType(request.getType().mark());
		instance.setCreated(DateUtil.current());
		return instance;
	}
	
	public static final CfgRole newCfgRole(CreateRoleRequest request) {
		CfgRole instance = new CfgRole();
		instance.setName(request.getName());
		instance.setMemo(request.getMemo());
		int time = DateUtil.current();
		instance.setCreated(time);
		instance.setUpdated(time);
		return instance;
	}
}
