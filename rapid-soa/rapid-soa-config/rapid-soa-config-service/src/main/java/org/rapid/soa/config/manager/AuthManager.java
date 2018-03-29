package org.rapid.soa.config.manager;

import javax.annotation.Resource;

import org.rapid.core.Assert;
import org.rapid.soa.config.bean.entity.CfgAuthority;
import org.rapid.soa.config.bean.entity.CfgGateway;
import org.rapid.soa.config.bean.entity.CfgModular;
import org.rapid.soa.config.bean.entity.CfgRole;
import org.rapid.soa.config.bean.enums.ConfigCode;
import org.rapid.soa.config.bean.request.CreateAuthorityRequest;
import org.rapid.soa.config.bean.request.CreateGatewayRequest;
import org.rapid.soa.config.bean.request.CreateModularRequest;
import org.rapid.soa.config.bean.request.CreateRoleRequest;
import org.rapid.soa.config.bean.request.ModifyModularRequest;
import org.rapid.soa.config.bean.request.ModifyRoleRequest;
import org.rapid.soa.config.internal.EntityGenerator;
import org.rapid.soa.config.mybatis.dao.CfgAuthorityDao;
import org.rapid.soa.config.mybatis.dao.CfgGatewayDao;
import org.rapid.soa.config.mybatis.dao.CfgModularDao;
import org.rapid.soa.config.mybatis.dao.CfgRoleDao;
import org.rapid.util.DateUtil;
import org.rapid.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AuthManager {
	
	@Resource
	private CfgRoleDao cfgRoleDao;
	@Resource
	private CfgModularDao cfgModularDao;
	@Resource
	private CfgGatewayDao cfgGatewayDao;
	@Resource
	private CfgAuthorityDao cfgAuthorityDao;
	
	public int createRole(CreateRoleRequest request) {
		CfgRole role = EntityGenerator.newCfgRole(request);
		cfgRoleDao.insert(role);
		return role.getId();
	}
	
	public void modifyRole(ModifyRoleRequest request) {
		CfgRole role = cfgRoleDao.getByKey(request.getId());
		Assert.notNull(ConfigCode.ROLE_NOT_EXIST, role);
		if (StringUtil.hasText(request.getName()))
			role.setName(request.getName());
		if (StringUtil.hasText(request.getMemo()))
			role.setMemo(request.getMemo());
		role.setUpdated(DateUtil.current());
		cfgRoleDao.update(role);
	}
	
	public int createGateway(CreateGatewayRequest request) {
		CfgGateway gateway = EntityGenerator.newCfgGateway(request);
		cfgGatewayDao.insert(gateway);
		return gateway.getId();
	}
	
	public void deleteGateway(int id) {
		cfgGatewayDao.deleteByKey(id);
	}

	@Transactional
	public int createModular(CreateModularRequest request) {
		CfgModular parent = null;
		if (0 != request.getParent()) {
			parent = modular(request.getParent());
			Assert.notNull(ConfigCode.MODULAR_NOT_EXIST, parent);
		}
		CfgModular modular = EntityGenerator.newCfgModular(parent, request.getName());
		if (null != parent)
			cfgModularDao.insertUpdate(parent.getTrunk(), parent.getRight());
		cfgModularDao.insert(modular);
		return modular.getId();
	}
	
	public void modifyModular(ModifyModularRequest request) {
		CfgModular modular = modular(request.getId());
		Assert.notNull(ConfigCode.MODULAR_NOT_EXIST, modular);
		modular.setName(request.getName());
		modular.setUpdated(DateUtil.current());
		cfgModularDao.update(modular);
	}
	
	public int createAuthority(CreateAuthorityRequest request) {
		switch (request.getType()) {
		case ROLE:
			CfgRole role = cfgRoleDao.getByKey(request.getTid());
			Assert.notNull(ConfigCode.ROLE_NOT_EXIST, role);
			CfgModular modular = cfgModularDao.getByKey(request.getAuthId());
			Assert.notNull(ConfigCode.MODULAR_NOT_EXIST, modular);
			break;
		case MODULAR:
			modular = cfgModularDao.getByKey(request.getTid());
			Assert.notNull(ConfigCode.MODULAR_NOT_EXIST, modular);
			CfgGateway gateway = cfgGatewayDao.getByKey(request.getAuthId());
			Assert.notNull(ConfigCode.GATEWAY_NOT_EXIST, gateway);
			break;
		default:
			break;
		}
		CfgAuthority authority = EntityGenerator.newCfgAuthority(request);
		cfgAuthorityDao.insert(authority);
		return authority.getId();
	}
	
	public void deleteAuthority(int id) {
		cfgAuthorityDao.deleteByKey(id);
	}
	
	public CfgModular modular(int id) {
		return cfgModularDao.getByKey(id);
	}
}
