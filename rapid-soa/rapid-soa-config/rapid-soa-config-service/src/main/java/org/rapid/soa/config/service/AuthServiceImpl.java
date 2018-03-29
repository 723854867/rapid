package org.rapid.soa.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import javax.annotation.Resource;

import org.rapid.core.Assert;
import org.rapid.soa.config.api.AuthService;
import org.rapid.soa.config.bean.entity.CfgAuthority;
import org.rapid.soa.config.bean.entity.CfgGateway;
import org.rapid.soa.config.bean.entity.CfgModular;
import org.rapid.soa.config.bean.info.ModularInfo;
import org.rapid.soa.config.bean.model.query.AuthorityQuery;
import org.rapid.soa.config.bean.model.query.GatewayQuery;
import org.rapid.soa.config.bean.model.query.ModularQuery;
import org.rapid.soa.config.bean.request.CreateAuthorityRequest;
import org.rapid.soa.config.bean.request.CreateGatewayRequest;
import org.rapid.soa.config.bean.request.CreateModularRequest;
import org.rapid.soa.config.bean.request.CreateRoleRequest;
import org.rapid.soa.config.bean.request.ModifyModularRequest;
import org.rapid.soa.config.bean.request.ModifyRoleRequest;
import org.rapid.soa.config.manager.AuthManager;
import org.rapid.soa.config.mybatis.dao.CfgAuthorityDao;
import org.rapid.soa.config.mybatis.dao.CfgGatewayDao;
import org.rapid.soa.config.mybatis.dao.CfgModularDao;
import org.rapid.soa.core.bean.enums.SoaCode;
import org.rapid.soa.core.bean.request.SoaIdRequest;
import org.rapid.util.CollectionUtil;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Resource
	private AuthManager authManager;
	@Resource
	private CfgGatewayDao cfgGatewayDao;
	@Resource
	private CfgModularDao cfgModularDao;
	@Resource
	private CfgAuthorityDao cfgAuthorityDao;
	
	@Override
	public CfgGateway gateway(String path) {
		return cfgGatewayDao.queryUnique(new GatewayQuery().path(path));
	}
	
	@Override
	public List<ModularInfo> modulars() {
		List<CfgModular> modulars = cfgModularDao.getAllList();
		if (CollectionUtil.isEmpty(modulars))
			return CollectionUtil.emptyList();
		Map<Long, List<CfgModular>> trunks = new HashMap<Long, List<CfgModular>>();
		for (CfgModular modular : modulars) {
			List<CfgModular> list = trunks.get(modular.getTrunk());
			if (null == list) {
				list = new ArrayList<CfgModular>();
				trunks.put(modular.getTrunk(), list);
			} 
			list.add(modular);
		}
		List<ModularInfo> infos = new ArrayList<ModularInfo>();
		for (Entry<Long, List<CfgModular>> entry : trunks.entrySet()) {
			List<CfgModular> list = entry.getValue();
			list.sort((o1, o2) -> o1.getLeft() - o2.getLeft());
			Stack<ModularInfo> stack = new Stack<ModularInfo>();
			for (CfgModular modular : list) 
				_build(stack, modular);
			infos.add(stack.firstElement());
		}
		return infos;
	}
	
	private static void _build(Stack<ModularInfo> stack, CfgModular modular) {
		if (stack.isEmpty()) 
			stack.push(new ModularInfo(modular));
		else {
			ModularInfo info = stack.peek();
			while (info.getLayer() + 1 != modular.getLayer()) {
				stack.pop();
				info = stack.peek();
			}
			ModularInfo current = info.append(modular);
			if (modular.getLeft() + 1 == modular.getRight())			// 叶子节点
				return;
			stack.push(current);
		}
	}
	
	@Override
	public void deleteGateway(SoaIdRequest request) {
		authManager.deleteGateway(request.getId());
	}
	
	@Override
	public void deleteAuthority(SoaIdRequest request) {
		authManager.deleteAuthority(request.getId());
	}
	
	@Override
	public int createRole(CreateRoleRequest request) {
		return authManager.createRole(request);
	}
	
	@Override
	public void modifyRole(ModifyRoleRequest request) {
		authManager.modifyRole(request);
	}
	
	@Override
	public int createGateway(CreateGatewayRequest request) {
		return authManager.createGateway(request);
	}
	
	@Override
	public int createModular(CreateModularRequest request) {
		return authManager.createModular(request);
	}
	
	@Override
	public void modifyModular(ModifyModularRequest request) {
		authManager.modifyModular(request);
	}
	
	@Override
	public int createAuthority(CreateAuthorityRequest request) {
		return authManager.createAuthority(request);
	}
	
	@Override
	public void auth(CfgGateway gateway, Set<Integer> own) {
		if (!gateway.isAuth())
			return;
		List<CfgAuthority> list = cfgAuthorityDao.queryList(new AuthorityQuery().gatewayId(gateway.getId()));
		if (!CollectionUtil.isEmpty(list)) {
			Set<Integer> set = new HashSet<Integer>();
			list.forEach(item -> set.add(item.getTid()));
			List<CfgModular> cmodulars = cfgModularDao.queryList(new ModularQuery().ids(set));
			Set<Integer> all = cfgModularDao.modulars(cmodulars);
			all.retainAll(own);
			Assert.isTrue(SoaCode.AUTH_FAIL, !CollectionUtil.isEmpty(all));
		}
	}
}
