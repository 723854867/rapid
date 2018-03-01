package org.rapid.dubbo;

import org.rapid.core.ResourceLoader;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@Configuration
@ComponentScan("org.rapid")
@PropertySource("classpath:conf/dubbo.properties")
public class DubboConfig extends ResourceLoader {
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig application = new ApplicationConfig();
		application.setName(getProperty("application.name", String.class));
		application.setOrganization(getProperty("application.owner", String.class));
		application.setOrganization(getProperty("application.environment", String.class));
		application.setOrganization(getProperty("application.organization", String.class));
		return application;
	}

	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registry = new RegistryConfig();
		String id = getProperty("registry.id", null, String.class);
		if (StringUtil.hasText(id))
			registry.setId(id);
		String protocol = getProperty("registry.protocol", null, String.class);
		if (StringUtil.hasText(protocol))
			registry.setProtocol(protocol);
		Integer port = getProperty("registry.port", null, Integer.class);
		if (null != port)
			registry.setPort(port);
		String username = getProperty("registry.username", null, String.class);
		if (StringUtil.hasText(username))
			registry.setUsername(username);
		String password = getProperty("registry.password", null, String.class);
		if (StringUtil.hasText(password))
			registry.setPassword(password);
		String transporter = getProperty("registry.transporter", null, String.class);
		if (StringUtil.hasText(transporter))
			registry.setTransporter(transporter);
		Integer timeout = getProperty("registry.timeout", null, Integer.class);
		if (null != timeout)
			registry.setTimeout(timeout);
		Integer session = getProperty("registry.session", null, Integer.class);
		if (null != session)
			registry.setSession(session);
		registry.setFile(getProperty("registry.file",String.class));
		registry.setAddress(getProperty("registry.address", String.class));
		return registry;
	}
	
	@Bean
	@Conditional(ProviderCondition.class)
	public ProtocolConfig protocolConfig() {
		ProtocolConfig config = new ProtocolConfig();
		Integer port = getProperty("protocol.port", null, Integer.class);
		config.setPort(null == port ? -1 : port);
		String name = getProperty("protocol.type", null, String.class);
		config.setName(StringUtil.hasText(name) ? name : "dubbo");
		String threadpool = getProperty("protocol.threadpool", null, String.class);
		config.setThreadpool(StringUtil.hasText(threadpool) ? threadpool : "fixed");
		Integer threads = getProperty("protocol.threads", null, Integer.class);
		config.setThreads(null != threads ? threads : 100);
		Integer iothreads = getProperty("protocol.iothreads", null, Integer.class);
		config.setIothreads(null != iothreads ? iothreads : Runtime.getRuntime().availableProcessors() + 1);
		Integer accepts = getProperty("protocol.accepts", null, Integer.class);
		config.setAccepts(null != accepts ? accepts : 0);
		Integer payload = getProperty("protocol.payload", null, Integer.class);
		config.setPayload(null != payload ? payload : 88388608);
		return config;
	}
	
	@Bean
	@Conditional(ProviderCondition.class)
	public ProviderConfig providerConfig() {
		ProviderConfig config = new ProviderConfig();
		config.setRetries(0);
		config.setFilter("exceptionFilter,-exception");
		config.setTimeout(getProperty("provider.timeout", 3000, Integer.class));
		return config;
	}
}
