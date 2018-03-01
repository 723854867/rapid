package org.rapid.core.initial;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.rapid.core.ResourceLoader;
import org.rapid.core.condition.MQProviderCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@Conditional(MQProviderCondition.class)
@PropertySource("classpath:conf/activemq.properties")
public class MQProviderBoot extends ResourceLoader {

	@Bean("jmsFactory")
	public PooledConnectionFactory jmsFactory() {
		ActiveMQConnectionFactory amc = new ActiveMQConnectionFactory();
		amc.setBrokerURL(getProperty("activemq.brokerUrl", String.class));
		Boolean trustAllPackages = getProperty("activemq.trustAllPackages", null, Boolean.class);
		if (null != trustAllPackages)
			amc.setTrustAllPackages(trustAllPackages);
		amc.setUseAsyncSend(getProperty("activemq.useAsyncSend", false, boolean.class));
		String username = getProperty("activemq.username", null, String.class);
		if (null != username)
			amc.setUserName(username);
		String password = getProperty("activemq.password", null, String.class);
		if (null != password)
			amc.setPassword(username);
		PooledConnectionFactory factory = new PooledConnectionFactory(amc);
		factory.setMaxConnections(getProperty("activemq.maxConnections", 100, Integer.class));
		return factory;
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory =  new CachingConnectionFactory();
		connectionFactory.setTargetConnectionFactory(jmsFactory());
		connectionFactory.setSessionCacheSize(getProperty("activemq.sessionCacheSize", 100, Integer.class));
		return connectionFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setExplicitQosEnabled(getProperty("activemq.explicitQosEnabled", true, boolean.class));
		jmsTemplate.setDeliveryPersistent(getProperty("activemq.deliveryPersistent", true, boolean.class));
		return jmsTemplate;
	}
}
