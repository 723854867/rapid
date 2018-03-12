package org.rapid.core.initial;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.core.condition.MQProviderCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@Conditional(MQProviderCondition.class)
public class MQProviderBoot extends RapidConfiguration {

	@Bean("jmsFactory")
	public PooledConnectionFactory jmsFactory() {
		ActiveMQConnectionFactory amc = new ActiveMQConnectionFactory();
		amc.setBrokerURL(RapidConfiguration.get(CoreConsts.ACTIVEMQ_BROKER_URL, true));
		Boolean trustAllPackages = RapidConfiguration.get(CoreConsts.ACTIVEMQ_TRUST_ALL_PACKAGES, false);
		if (null != trustAllPackages)
			amc.setTrustAllPackages(trustAllPackages);
		amc.setUseAsyncSend(RapidConfiguration.get(CoreConsts.ACTIVEMQ_USE_ASYNC_SEND, false));
		String username = RapidConfiguration.get(CoreConsts.ACTIVEMQ_USERNAME, false);
		if (null != username)
			amc.setUserName(username);
		String password = RapidConfiguration.get(CoreConsts.ACTIVEMQ_PASSWORD, false);
		if (null != password)
			amc.setPassword(username);
		PooledConnectionFactory factory = new PooledConnectionFactory(amc);
		factory.setMaxConnections(RapidConfiguration.get(CoreConsts.ACTIVEMQ_MAX_CONNECTIONS, false));
		return factory;
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory =  new CachingConnectionFactory();
		connectionFactory.setTargetConnectionFactory(jmsFactory());
		connectionFactory.setSessionCacheSize(RapidConfiguration.get(CoreConsts.ACTIVEMQ_SESSION_CACHE_SIZE, false));
		return connectionFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDeliveryPersistent(RapidConfiguration.get(CoreConsts.ACTIVEMQ_DELIVERY_PERSISTENT, false));
		jmsTemplate.setExplicitQosEnabled(RapidConfiguration.get(CoreConsts.ACTIVEMQ_EXPLICIT_QOS_ENABLED, false));
		return jmsTemplate;
	}
}
