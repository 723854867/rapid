package org.rapid.core.http;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.rapid.core.SpringContextUtil;
import org.rapid.core.bean.exception.BizException;
import org.rapid.core.serialize.SerializeUtil;
import org.rapid.core.serialize.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.Expose;

import okhttp3.Request;
import okhttp3.Response;

public abstract class HttpRequest<RESPONSE extends HttpResponse> implements Serializable {

	private static final long serialVersionUID = -7850852425551649066L;
	
	private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);
	
	private String prefix;
	// 请求地址
	@Expose(serialize = false, deserialize = false)
	protected String url;
	@Expose(serialize = false, deserialize = false)
	protected Class<RESPONSE> clazz;
	@Expose(serialize = false, deserialize = false)
	protected HttpClient httpClient;
	// 序列化类
	@Expose(serialize = false, deserialize = false)
	protected Serializer serializer;
	// 反序列化类
	@Expose(serialize = false, deserialize = false)
	protected Serializer deserializer;
	// 参数
	@Expose(serialize = false, deserialize = false)
	protected Map<String, String> params;
	// 请求头
	@Expose(serialize = false, deserialize = false)
	protected Map<String, String> headers;
	
	@SuppressWarnings("unchecked")
	protected HttpRequest(String prefix, String url, Serializer serializer, Serializer deserializer) {
		this.url = url;
		this.prefix = prefix;
		this.serializer = serializer;
		this.deserializer = deserializer;
		this.params = new HashMap<String, String>();
		this.headers = new HashMap<String, String>();
		Type superType = getClass().getGenericSuperclass();   
		Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
		this.clazz = (Class<RESPONSE>) generics[0];
		this.httpClient = SpringContextUtil.getBean("httpClient", HttpClient.class);
	}
	
	public String url() {
		return url;
	}
	
	public void addParam(String key, Object value) {
		this.params.put(key, value.toString().trim());
	}
	
	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}
	
	public RESPONSE execute() {
		try {
			Response response = this.httpClient.request(request());
			logger.info("{} 请求响应  - {} - {}!", prefix, url, SerializeUtil.GSON_ANNO.toJson(response));
			return response(response);
		} catch (Exception e) {
			logger.error("{} 请求失败  - {} - {}!", prefix, url, SerializeUtil.GSON_ANNO.toJson(this), e);
			throw new BizException(e);
		}
	}
	
	/**
	 * 默认使用get方式提交
	 */
	protected Request request() {
		Request.Builder rb = new Request.Builder().url(url);
		for (Entry<String, String> entry : headers.entrySet())
			rb.addHeader(entry.getKey(), entry.getValue());
		return rb.build();
	}
	
	/**
	 * 默认直接使用序列化类来序列化
	 */
	protected RESPONSE response(Response response) throws IOException {
		return deserializer.deserial(response.body().bytes(), clazz);
	}
}
