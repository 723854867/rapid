package org.rapid.core.initial;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.rapid.core.ResourceLoader;
import org.rapid.core.condition.MQConsumerCondition;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Conditional(MQConsumerCondition.class)
@PropertySource("classpath:conf/activemq.properties")
public class MQConsumerBoot extends ResourceLoader {

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
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setDaemon(getProperty("activemq.daemon", true, boolean.class));
		taskExecutor.setMaxPoolSize(getProperty("activemq.maxPoolSize", 300, Integer.class));
		taskExecutor.setCorePoolSize(getProperty("activemq.corePoolSize", 150, Integer.class));
		taskExecutor.setKeepAliveSeconds(getProperty("activemq.keepAliveSeconds", 120, Integer.class));
		return taskExecutor;
	}
	
	@Bean
	@Scope("prototype")
	public MessageListenerContainer messageContainer() {
		DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
		messageListenerContainer.setConnectionFactory(connectionFactory());
		messageListenerContainer.setDestinationName(StringUtil.EMPTY);
		messageListenerContainer.setReceiveTimeout(getProperty("activemq.receiveTimeout", 1000, Integer.class));
		messageListenerContainer.setTaskExecutor(taskExecutor());
		messageListenerContainer.setConcurrentConsumers(getProperty("activemq.concurrentConsumers", 1, Integer.class));
		return messageListenerContainer;
	}
}
