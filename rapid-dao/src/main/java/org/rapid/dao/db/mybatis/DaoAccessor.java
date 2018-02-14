package org.rapid.dao.db.mybatis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.rapid.core.bean.exception.BizException;
import org.rapid.dao.db.mybatis.provider.SQLProvider;
import org.rapid.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoAccessor {
	
	private static final Logger logger = LoggerFactory.getLogger(DaoAccessor.class);

	private DaoConfig config;
	private final Map<Class<?>, DaoMeta> daos = new ConcurrentHashMap<Class<?>, DaoMeta>();
    private final Map<String, Boolean> msIdReconfig = new ConcurrentHashMap<String, Boolean>();
	
	public boolean isRegister(Class<?> daoClass) { 
		for (Class<?> clazz : daos.keySet()) {
			if (clazz.isAssignableFrom(daoClass))
				return true;
		}
		return false;
	}

	public void registerDao(String className) {
		try {
			registerDao(Class.forName(className));
		} catch (ClassNotFoundException e) {
			throw new BizException("注册通用Dao[" + className + "]失败，找不到该通用Dao!");
		}
	}

	public void registerDao(Class<?> daoClass) {
		if (!daos.containsKey(daoClass)) {
			DaoMeta meta = new DaoMeta(daoClass);
			if (null == daos.putIfAbsent(daoClass, meta))
				_daoInitial(meta);
		}
		// 自动注册继承的接口
		Class<?>[] interfaces = daoClass.getInterfaces();
		if (interfaces != null && interfaces.length > 0) {
			for (Class<?> sinterface : interfaces)
				registerDao(sinterface);
		}
	}

	private void _daoInitial(DaoMeta meta) {
		Method[] methods = meta.daoClass.getDeclaredMethods();
		for (Method method : methods) {
			Class<?> providerClass = null;
			if (method.isAnnotationPresent(SelectProvider.class)) {
				SelectProvider provider = method.getAnnotation(SelectProvider.class);
				providerClass = provider.type();
			} else if (method.isAnnotationPresent(InsertProvider.class)) {
				InsertProvider provider = method.getAnnotation(InsertProvider.class);
				providerClass = provider.type();
			} else if (method.isAnnotationPresent(DeleteProvider.class)) {
				DeleteProvider provider = method.getAnnotation(DeleteProvider.class);
				providerClass = provider.type();
			} else if (method.isAnnotationPresent(UpdateProvider.class)) {
				UpdateProvider provider = method.getAnnotation(UpdateProvider.class);
				providerClass = provider.type();
			} else {
				logger.debug("{} 存在未被标识的方法  - {}！", meta.daoClass.getCanonicalName(), method.getName());
				continue;
			}
			if (!SQLProvider.class.isAssignableFrom(providerClass)) 
				throw new BizException("{} - {} 的 provider 必须继承 org.rapid.dao.db.mybatis.provider.SQLProvider");
			try {
				SQLProvider<?> provider = (SQLProvider<?>) providerClass.getConstructor(Class.class, DaoAccessor.class).newInstance(meta.daoClass, this);
				meta.addMethod(method, provider);
			} catch (Exception e) {
				throw new BizException(e, "Provider 对象实例化失败！");
			}
		}
	}
	
	public void daoReconfig(Configuration configuration, Class<?> daoClass) {
		String prefix = null == daoClass ? StringUtil.EMPTY : daoClass.getCanonicalName();
		for (Object object : new ArrayList<Object>(configuration.getMappedStatements())) {
            if (!(object instanceof MappedStatement)) 
               continue;
            MappedStatement ms = (MappedStatement) object;
    		if (!(ms.getSqlSource() instanceof ProviderSqlSource))
    			continue;
            if (ms.getId().startsWith(prefix)) 
            	_reSetSqlSource(ms, daoClass);
        }
	}
	
	private void _reSetSqlSource(MappedStatement ms, Class<?> daoClass) {
		String msId = ms.getId();
		String method = MsUtil.getMethodName(ms);
		Method[] methods = daoClass.getDeclaredMethods();
		for (Method m : methods) {
			if (m.getName().equals(method)) {
				logger.debug("{} 为自定义方法，不重新设置 sqlsource!", msId);
				return;
			}
		}
		Boolean reconfig = msIdReconfig.get(msId);
    	if (null != reconfig && !reconfig)
    		return;
    	for (DaoMeta meta : daos.values()) {
    		if (!meta.daoClass.isAssignableFrom(daoClass))
    			continue;
    		MethodMeta methodMeta = meta.getMethod(method);
    		if (null == methodMeta)
    			continue;
    		methodMeta.setSqlSource(ms);
			msIdReconfig.put(msId, true);
            return;
    	}
    	msIdReconfig.put(msId, false);
	}
	
	public void setProperties(Properties properties) {
		config.setProperties(properties);
		//注册通用dao
        String daos = null;
        if (properties != null)
        	daos = properties.getProperty("db.mybatis.daos");
        if (StringUtil.hasText(daos)) {
            String[] arr = daos.split(",");
            for (String dao : arr) {
                if (dao.length() > 0)
                    registerDao(dao);
            }
        }
	}
	
	public DaoConfig getConfig() {
		return config;
	}
	
	private static class DaoMeta {
		private Class<?> daoClass;
		private Map<String, MethodMeta> methods = new HashMap<String, MethodMeta>();

		public DaoMeta(Class<?> clazz) {
			this.daoClass = clazz;
		}
		
		public void addMethod(Method method, SQLProvider<?> provider) {
			this.methods.put(method.getName(), new MethodMeta(method, provider));
		}
		
		public MethodMeta getMethod(String method) {
			return methods.get(method);
		}
	}
	
	@SuppressWarnings("unused")
	private static class MethodMeta {
		private static final XMLLanguageDriver languageDriver = new XMLLanguageDriver();
		private Method method;
		private SQLProvider<?> provider;
		public MethodMeta(Method method, SQLProvider<?> provider) {
			this.method = method;
			this.provider = provider;
		}
		public void setSqlSource(MappedStatement ms) {
			try {
				Method sqlMethod = provider.getClass().getMethod("effectiveSQL", MappedStatement.class);
				Class<?> returnType = sqlMethod.getReturnType();
	            if (returnType == Void.TYPE)			 //第一种，直接操作ms，不需要返回值
	            	provider.effectiveSQL(ms);
	            else if (SqlNode.class.isAssignableFrom(returnType)) {			//第二种，返回SqlNode
	                SqlNode sqlNode = (SqlNode) provider.effectiveSQL(ms);
	                DynamicSqlSource dynamicSqlSource = new DynamicSqlSource(ms.getConfiguration(), sqlNode);
	                _setSqlSource(ms, dynamicSqlSource);
	            } else if (returnType == String.class) {							//第三种，返回xml形式的sql字符串
	                String xmlSql = (String) provider.effectiveSQL(ms);
	                SqlSource sqlSource = _createSqlSource(ms, xmlSql);
	                _setSqlSource(ms, sqlSource);
	            } else 
	                throw new BizException("自定义Provider方法返回类型错误,可选的返回类型为void,SqlNode,String三种!");
			} catch (Exception e) {
			}
		}
		
		private void _setSqlSource(MappedStatement ms, SqlSource sqlSource) {
	        MetaObject msObject = SystemMetaObject.forObject(ms);
	        msObject.setValue("sqlSource", sqlSource);
	    }
		
		private SqlSource _createSqlSource(MappedStatement ms, String xmlSql) {
	        return languageDriver.createSqlSource(ms.getConfiguration(), "<script>\n\t" + xmlSql + "</script>", null);
	    }
	}
}
