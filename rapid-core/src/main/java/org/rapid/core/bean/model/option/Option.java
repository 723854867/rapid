package org.rapid.core.bean.model.option;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.rapid.core.bean.model.code.ICode;
import org.rapid.core.bean.model.message.Response;

@SuppressWarnings("unchecked")
public class Option<VALUE> implements Serializable {

	private static transient final Map<String, Option<?>> options = new ConcurrentHashMap<String, Option<?>>();

	private static final long serialVersionUID = -4013391922070099509L;
	
	protected String key;
	protected VALUE defaultValue;
	protected Class<VALUE> clazz;
	
	protected Option() {}
	
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
	public Option(String key, VALUE defaultValue) {
		if (null != options.putIfAbsent(key, this))
			throw new RuntimeException("rapid option [" + key + "] duplicated, option  must be unique!");
		this.key = key;
		this.defaultValue = defaultValue;
		if (null == defaultValue) {
			Type superType = getClass().getGenericSuperclass();   
			Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
			this.clazz = (Class<VALUE>) generics[0];
		} else
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
	
	public void setDefaultValue(VALUE defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public static Map<String, Option<?>> getOptions() {
		return options;
	}
	
	public static <T> Response<T> createResponse(ICode code) {
		StrOption option = option(code.key());
		Response<T> response = new Response<T>(code);
		response.setDesc(null == option ? code.desc() : option.getDefaultValue());
		return response;
	}
	
	public static <T> Response<T> handleResponse(Response<T> response) {
		StrOption option = option(response.getCodeRefer().key());
		response.setDesc(null == option ? response.getCodeRefer().desc() : option.getDefaultValue());
		return response;
	}
	
	public static final <OPTION extends Option<VALUE>, VALUE> OPTION option(String key) {
		Option<?> option = options.get(key);
		return null == option ? null : (OPTION) option;
	}
}
