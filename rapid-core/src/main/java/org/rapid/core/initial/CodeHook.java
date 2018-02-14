package org.rapid.core.initial;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.rapid.core.Rapid;
import org.rapid.core.bean.model.option.Option;
import org.rapid.core.bean.model.option.StrOption;
import org.rapid.util.Consts;
import org.rapid.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class CodeHook implements InitialHook {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeHook.class);
	
	@javax.annotation.Resource
	private Rapid rapid;
	@javax.annotation.Resource
	private PathMatchingResourcePatternResolver resourceResolver;
	
	@Override
	public void init() {
		String path = "classpath*:lang/error_code_" + rapid.getLocale().mark() + ".properties";
		logger.info("Error code initial start...", path);
		int total = 0;
		try {
			Resource[] resources = resourceResolver.getResources(path);
			for (Resource resource : resources) {
				logger.info("Error code load from {}...", resource.getURL());
				InputStream in = resource.getInputStream();
				Properties properties = new Properties();
				properties.load(new InputStreamReader(in, Consts.UTF_8));
				Set<Entry<Object, Object>> set = properties.entrySet();
				int count = 0;
				for (Entry<Object, Object> entry : set) {
					String key = entry.getKey().toString();
					if (null == entry.getValue()) {
						logger.warn("Error code {} is null, this will be ignored!", key);
						continue;
					}
					StrOption option = Option.option(key);
					String value = entry.getValue().toString().trim();
					if (null == option) {
						if (!StringUtil.hasText(value)) {
							logger.warn("Error code {} is empty, value will be ignored!", key);
							continue;
						} else {
							option = new StrOption(key, value);
							count += 1;
						}
					} else 
						logger.debug("Error code {} has already exist, the current value [{}] will be ignored!", key, value);
				}
				total += count;
				logger.info("Error code load {} records from {}", count, resource.getURL());
				in.close();
			}
		} catch (Exception e) {
			logger.error("Error code load failure, system will closed!", e);
			System.exit(1);
		}
		logger.info("Error code initial finish, total {} records were loaded!", total);
	}
}
