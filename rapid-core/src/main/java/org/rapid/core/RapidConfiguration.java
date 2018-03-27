package org.rapid.core;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.model.option.Option;
import org.rapid.util.Consts;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class RapidConfiguration {
	
	public static final ConfigurableConversionService CONVERSION_SERVICE = new DefaultConversionService();
	public static final PathMatchingResourcePatternResolver RESOLVER = new PathMatchingResourcePatternResolver();
	
	private static final Map<String, String> properties = new HashMap<String, String>();
	
	static {
		try {
			Resource[] resources = RESOLVER.getResources("classpath*:conf/rapid.properties");
			for (Resource resource : resources) {
				InputStream in = resource.getInputStream();
				Properties properties = new Properties();
				properties.load(new InputStreamReader(in, Consts.UTF_8));
				for (Entry<Object, Object> entry : properties.entrySet()) 
					RapidConfiguration.properties.put(entry.getKey().toString(), entry.getValue().toString());
				in.close();
			}
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	public static final <T> T get(Option<T> option, boolean necessary) {
		String property = properties.get(option.getKey());
		T value = null == property ? option.getDefaultValue() : CONVERSION_SERVICE.convert(property, option.getClazz());
		return necessary ? Assert.notNull("property [" + option.getKey() + "] is no specified!", value) : value;
	}
	
	public static final Resource[] getResources(String locationPattern) {
		try {
			return RESOLVER.getResources(locationPattern);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
}
