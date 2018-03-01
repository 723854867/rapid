package org.rapid.core;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.annotation.Resource;

import org.rapid.util.Consts;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public abstract class ResourceLoader {

	@Resource
	protected AbstractEnvironment environment;
	@Resource
	protected PathMatchingResourcePatternResolver resourceResolver;
	
	protected Properties getproperty(String location) throws Exception { 
		org.springframework.core.io.Resource resource = resourceResolver.getResource(location);
		InputStream in = resource.getInputStream();
		Properties properties = new Properties();
		properties.load(new InputStreamReader(in, Consts.UTF_8));
		in.close();
		return properties;
	}
	
	protected Properties[] getProperties(String location) throws Exception { 
		org.springframework.core.io.Resource[] resources = resourceResolver.getResources(location);
		Properties[] arr = new Properties[resources.length];
		for (int i = 0, len = resources.length; i < len; i++) {
			InputStream in = resources[i].getInputStream();
			arr[i] = new Properties();
			arr[i].load(new InputStreamReader(in, Consts.UTF_8));
			in.close();
			
		}
		return arr;
	}
	
	protected <T> T getProperty(String key, Class<T> clazz) {
		T value = environment.getProperty(key, clazz);
		return Assert.notNull("property [" + key + "] is no specified!", value);
	}
	
	protected <T> T getProperty(String key, T defaultValue, Class<T> clazz) {
		T value = environment.getProperty(key, clazz, defaultValue);
		return value;
	}
	
	public static final Properties loadProperties(String location) throws Exception { 
		org.springframework.core.io.Resource resource = new PathMatchingResourcePatternResolver().getResource(location);
		InputStream in = resource.getInputStream();
		Properties properties = new Properties();
		properties.load(new InputStreamReader(in, Consts.UTF_8));
		in.close();
		return properties;
	}
}
