package org.rapid.soa.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.rapid.core.Assert;
import org.rapid.soa.config.api.GatewayService;
import org.rapid.soa.config.bean.entity.CfgAuthority;
import org.rapid.soa.config.bean.entity.CfgGateway;
import org.rapid.soa.config.bean.entity.CfgModular;
import org.rapid.soa.config.bean.info.ModularInfo;
import org.rapid.soa.config.bean.model.query.AuthorityQuery;
import org.rapid.soa.config.bean.model.query.GatewayQuery;
import org.rapid.soa.config.bean.model.query.ModularQuery;
import org.rapid.soa.config.mybatis.dao.CfgAuthorityDao;
import org.rapid.soa.config.mybatis.dao.CfgGatewayDao;
import org.rapid.soa.config.mybatis.dao.CfgModularDao;
import org.rapid.soa.core.bean.enums.SoaCode;
import org.rapid.util.CollectionUtil;
import org.springframework.stereotype.Service;

@Service("gatewayService")
public class GatewayServiceImpl implements GatewayService {

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
		return null;
	}
	
	private List<ModularInfo> _build(List<CfgModular> modulars) {
		modulars.sort((o1, o2) -> o1.getLeft() - o2.getLeft());
		Map<Integer, ModularInfo> trunks = new HashMap<Integer, ModularInfo>();
		for (CfgModular modular : modulars) {
			ModularInfo info = trunks.get(modular.getTrunk());
			if (null == info) {
				info = new ModularInfo(modular);
				trunks.put(modular.getTrunk(), info);
			} else {
				
			}
		}
		return null;
	}
	
	@Override
	public void auth(CfgGateway gateway, Set<Integer> own) {
		if (!gateway.isAuth())
			return;
		List<CfgAuthority> list = cfgAuthorityDao.queryList(new AuthorityQuery().gatewayId(gateway.getId()));
		if (!CollectionUtil.isEmpty(list)) {
			Set<Integer> set = new HashSet<Integer>();
			list.forEach(item -> set.add(item.getModularId()));
			List<CfgModular> cmodulars = cfgModularDao.queryList(new ModularQuery().ids(set));
			Set<Integer> all = cfgModularDao.modulars(cmodulars);
			all.retainAll(own);
			Assert.isTrue(SoaCode.AUTH_FAIL, !CollectionUtil.isEmpty(all));
		}
	}
}
