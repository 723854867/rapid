package org.rapid.core.config;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.rapid.core.Rapid;
import org.rapid.core.ZkUtil;
import org.rapid.core.initial.InitialHook;
import org.rapid.core.serialize.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分布式配置
 * 
 * @author lynn
 *
 * @param <T>
 */
public abstract class DistributeConfigLoader<T extends DistributeConfig> implements InitialHook {

	private static final Logger logger = LoggerFactory.getLogger(DistributeConfigLoader.class);

	private T config;
	@Resource
	private Rapid rapid;
	private Class<T> clazz;
	@Resource
	private ZkClient zkClient;
	private String configPath;
	@Resource
	private Serializer serializer;
	
	@SuppressWarnings("unchecked")
	protected DistributeConfigLoader(String configPath) {
		Type superType = getClass().getGenericSuperclass();   
		Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
		this.clazz = (Class<T>) generics[0];
		this.configPath = configPath;
	}
	
	@Override
	public void init() {
		this.configPath = this.configPath + "/" + rapid.getEnv().name().toLowerCase();
		if (!zkClient.exists(configPath))
			zkClient.createPersistent(configPath, true);
		T temp = ZkUtil.read(zkClient, configPath, clazz, serializer);
		config = null == temp ? defaultConfiguration() : temp;
		zkClient.subscribeDataChanges(configPath, new IZkDataListener() {
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				logger.info("Distribute config deleted!");
				config = defaultConfiguration();
			}
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				logger.info("Distribute config changed!");
				if (null == data)
					return;
				if (data.getClass() != byte[].class) {
					logger.error("Distribute data type error!");
					return;
				}
				byte[] buffer = (byte[]) data;
				try {
					T temp = serializer.deserial(buffer, clazz);
					config = temp;
				} catch (Exception e) {
					logger.error("Distribute config change failure!", e);
				}
			}
		});
	}

	public T getConfig() {
		return config;
	}
	
	public void resetConfig(T config) { 
		ZkUtil.writeJson(zkClient, config, configPath);
		this.config = config;
	}
	
	@Override
	public int priority() {
		return 10;
	}
	
	protected abstract T defaultConfiguration();
}
