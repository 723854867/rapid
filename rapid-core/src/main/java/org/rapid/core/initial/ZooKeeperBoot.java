package org.rapid.core.initial;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.rapid.core.Assert;
import org.rapid.core.ResourceLoader;
import org.rapid.core.bean.enums.Env;
import org.rapid.core.condition.ZookeeperCondition;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(ZookeeperCondition.class)
public class ZooKeeperBoot extends ResourceLoader {

	@Bean
	public ZkClient zkClient() throws Exception {
		Properties rproperties = getproperty("classpath:conf/rapid.properties");
		String env = rproperties.getProperty("rapid.env", Env.LOCAL.name()).toLowerCase();
		
		// 再读取指定的zookeeper的配置文件
		Properties[] properties = getProperties("classpath*:conf/zookeeper_" + env + ".properties");
		Map<String, String> configs = new HashMap<String, String>() {
			private static final long serialVersionUID = -1537069912786692084L;
			{
				put("zoopeeker.servers", null);
				put("zookeeper.serializer", null);
				put("zookeeper.sessionTimeout", null);
				put("zookeeper.connectionTimeout", null);
			}
		};
		for (Properties property : properties) {
			for (Entry<String, String> entry : configs.entrySet()) {
				if (StringUtil.hasText(entry.getValue()))
					continue;
				String value = property.getProperty(entry.getKey());
				if (StringUtil.hasText(value))
					entry.setValue(value);
			}
		}
		Class<?> clazz = Class.forName(_getProperty(configs, "zookeeper.serializer"));
		ZkSerializer zkSerializer = (ZkSerializer) clazz.newInstance();
		return new ZkClient(_getProperty(configs, "zoopeeker.servers"), Integer.valueOf(_getProperty(configs, "zookeeper.sessionTimeout")),
				Integer.valueOf(_getProperty(configs, "zookeeper.connectionTimeout")), zkSerializer);
	}

	private String _getProperty(Map<String, String> configs, String key) {
		String value = configs.get(key);
		return Assert.notNull("property [" + key + "] is no specified!", value);
	}
}
