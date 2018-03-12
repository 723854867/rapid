package org.rapid.core.initial;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.exception.BizException;
import org.rapid.core.condition.ZookeeperCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(ZookeeperCondition.class)
public class ZooKeeperBoot {

	@Bean
	public ZkClient zkClient() throws Exception {
		ZkSerializer zkSerializer;
		try {
			Class<?> clazz = Class.forName(RapidConfiguration.get(CoreConsts.ZOOKEEPER_SERIALIZER, true));
			zkSerializer = (ZkSerializer) clazz.newInstance();
		} catch (Exception e) {
			throw new BizException(e);
		} 
		return new ZkClient(RapidConfiguration.get(CoreConsts.ZOOKEEPER_SERVERS, true),
				RapidConfiguration.get(CoreConsts.ZOOKEEPER_SESSION_TIMEOUT, true),
				RapidConfiguration.get(CoreConsts.ZOOKEEPER_CONNECTION_TIMEOUT, true), zkSerializer);
	}
}
