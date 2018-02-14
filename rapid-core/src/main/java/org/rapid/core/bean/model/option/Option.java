package org.rapid.core.bean.model.option;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.rapid.core.bean.enums.Env;
import org.rapid.core.bean.enums.Locale;

@SuppressWarnings("unchecked")
public class Option<VALUE> {

	private static final Map<String, Option<?>> options = new ConcurrentHashMap<String, Option<?>>();

	protected String key;
	protected VALUE defaultValue;
	protected Class<VALUE> clazz;
	
	/**
	 * 子类
	 */
	protected Option(String key) {
		if (null != options.putIfAbsent(key, this))
			throw new RuntimeException("rapid option duplicated, option must be unique!");
		this.key = key;
		Type superType = getClass().getGenericSuperclass();   
		Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
		this.clazz = (Class<VALUE>) generics[0];
	}
	
	/**
	 * 没有默认值
	 */
	protected Option(String key, Class<VALUE> clazz) {
		if (null != options.putIfAbsent(key, this))
			throw new RuntimeException("rapid option duplicated, option must be unique!");
		this.key = key;
		this.clazz = clazz;
	}

	/**
	 * 有默认值
	 */
	protected Option(String key, VALUE defaultValue) {
		if (null != options.putIfAbsent(key, this))
			throw new RuntimeException("rapid option duplicated, option must be unique!");
		this.key = key;
		this.defaultValue = defaultValue;
		this.clazz = (Class<VALUE>) defaultValue.getClass();
	}

	public String getKey() {
		return key;
	}
	
	public Class<VALUE> getClazz() {
		return clazz;
	}

	public VALUE getDefaultValue() {
		return defaultValue;
	}
	
	public static Map<String, Option<?>> getOptions() {
		return options;
	}

	public static final <OPTION extends Option<VALUE>, VALUE> OPTION option(String key) {
		Option<?> option = options.get(key);
		return null == option ? null : (OPTION) option;
	}
	
	public static final Option<Env> RAPID_ENV = new Option<Env>("rapid.env", Env.LOCAL);
	public static final Option<Locale> RAPID_LOCALE = new Option<Locale>("rapid.locale", Locale.ZH_CN);
}
