package org.rapid.core.initial;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.core.condition.MQConsumerCondition;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Conditional(MQConsumerCondition.class)
public class MQConsumerBoot {

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
			amc.setPassword(password);
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
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setDaemon(RapidConfiguration.get(CoreConsts.ACTIVEMQ_DAEMON, false));
		taskExecutor.setMaxPoolSize(RapidConfiguration.get(CoreConsts.ACTIVEMQ_MAX_POOL_SIZE, false));
		taskExecutor.setCorePoolSize(RapidConfiguration.get(CoreConsts.ACTIVEMQ_CORE_POOL_SIZE, false));
		taskExecutor.setKeepAliveSeconds(RapidConfiguration.get(CoreConsts.ACTIVEMQ_KEEP_ALIVE_SECONDS, false));
		return taskExecutor;
	}
	
	@Bean
	@Scope("prototype")
	public MessageListenerContainer messageContainer() {
		DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
		messageListenerContainer.setConnectionFactory(connectionFactory());
		messageListenerContainer.setDestinationName(StringUtil.EMPTY);
		messageListenerContainer.setReceiveTimeout((long) RapidConfiguration.get(CoreConsts.ACTIVEMQ_RECEIVE_TIMEOUT, false));
		messageListenerContainer.setTaskExecutor(taskExecutor());
		messageListenerContainer.setConcurrentConsumers(RapidConfiguration.get(CoreConsts.ACTIVEMQ_CONCURRENT_CONSUMERS, false));
		return messageListenerContainer;
	}
}
