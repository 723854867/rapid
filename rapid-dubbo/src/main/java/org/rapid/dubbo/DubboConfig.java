package org.rapid.dubbo;

import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@Configuration
@ComponentScan("org.rapid")
public class DubboConfig {
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig application = new ApplicationConfig();
		application.setName(RapidConfiguration.get(CoreConsts.DUBBO_APP_NAME, true));
		application.setOrganization(RapidConfiguration.get(CoreConsts.DUBBO_APP_OWNER, true));
		application.setOrganization(RapidConfiguration.get(CoreConsts.DUBBO_APP_ENVIRONMENT, true));
		application.setOrganization(RapidConfiguration.get(CoreConsts.DUBBO_APP_ORGANZATION, true));
		return application;
	}

	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registry = new RegistryConfig();
		String id = RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_ID, false);
		if (StringUtil.hasText(id))
			registry.setId(id);
		String protocol = RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_PROTOCOL, false);
		if (StringUtil.hasText(protocol))
			registry.setProtocol(protocol);
		Integer port = RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_PORT, false);
		if (null != port)
			registry.setPort(port);
		String username = RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_USERNAME, false);
		if (StringUtil.hasText(username))
			registry.setUsername(username);
		String password = RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_PASSWORD, false);
		if (StringUtil.hasText(password))
			registry.setPassword(password);
		String transporter = RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_TRANSPORTER, false);
		if (StringUtil.hasText(transporter))
			registry.setTransporter(transporter);
		Integer timeout = RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_TIMEOUT, false);
		if (null != timeout)
			registry.setTimeout(timeout);
		Integer session = RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_SESSION, false);
		if (null != session)
			registry.setSession(session);
		registry.setFile(RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_FILE, true));
		registry.setAddress(RapidConfiguration.get(CoreConsts.DUBBO_REGISTRY_ADDRESS, true));
		return registry;
	}
	
	@Bean
	@Conditional(ProviderCondition.class)
	public ProtocolConfig protocolConfig() {
		ProtocolConfig config = new ProtocolConfig();
		config.setPort(RapidConfiguration.get(CoreConsts.DUBBO_PROTOCOL_PORT, false));
		config.setName(RapidConfiguration.get(CoreConsts.DUBBO_PROTOCOL_TYPE, false));
		config.setThreadpool(RapidConfiguration.get(CoreConsts.DUBBO_PROTOCOL_THREADPOOL, false));
		config.setThreads(RapidConfiguration.get(CoreConsts.DUBBO_PROTOCOL_THREADS, false));
		config.setIothreads(RapidConfiguration.get(CoreConsts.DUBBO_PROTOCOL_IOTHREADS, false));
		config.setAccepts(RapidConfiguration.get(CoreConsts.DUBBO_PROTOCOL_ACCEPTS, false));
		config.setPayload(RapidConfiguration.get(CoreConsts.DUBBO_PROTOCOL_PAYLOAD, false));
		return config;
	}
	
	@Bean
	@Conditional(ProviderCondition.class)
	public ProviderConfig providerConfig() {
		ProviderConfig config = new ProviderConfig();
		config.setRetries(0);
		config.setFilter("exceptionFilter,-exception");
		config.setTimeout(RapidConfiguration.get(CoreConsts.DUBBO_PROTOCOL_TIMEOUT, false));
		return config;
	}
}
