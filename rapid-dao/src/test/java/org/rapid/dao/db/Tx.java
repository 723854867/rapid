package org.rapid.dao.db;

import javax.annotation.Resource;

import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.model.code.Code;
import org.rapid.dao.bean.User;
import org.rapid.dao.db.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Tx {

	@Resource
	private UserDao userDao;
	
	@Transactional
	public void insert(User user) {
		userDao.insert(user);
		throw new BizException(Code.SYS_CONFIG_ERROR);
	}
}
