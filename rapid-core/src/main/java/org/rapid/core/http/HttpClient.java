package org.rapid.core.http;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.rapid.core.bean.enums.ContentType;
import org.rapid.core.bean.exception.RequestFailException;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
@Conditional(HttpCondition.class)
public class HttpClient {
	
	private OkHttpClient client;
	
	@PostConstruct
	public void init() {
		this.client = new OkHttpClient();
	}
	
	/**
	 * HTTP请求
	 */
	public <RESPONSE extends HttpResponse> Response request(Request request) throws RequestFailException {
		try {
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) 
				throw new RequestFailException(response.code(), response.message());
			return response;
		} catch (IOException e) {
			throw new RequestFailException(e);
		}
	}
	
	/**
	 * 异步的 get 请求
	 * 
	 * @param url
	 * @param callback
	 */
	public void getAsync(String url, Callback callback) { 
		client.newCall(new Request.Builder().url(url).build()).enqueue(callback);
	}
	
	/**
	 * post 异步请求
	 * 
	 * @param url
	 * @param content
	 * @param contentType
	 * @param callback
	 */
	public void postAsync(String url, String content, ContentType contentType, Callback callback) {
		RequestBody body = RequestBody.create(MediaType.parse(contentType.mark()), content);
		Request request = new Request.Builder().url(url).post(body).build();
		client.newCall(request).enqueue(callback);
	}
	
	/**
	 * 异步表单
	 * 
	 * @param url
	 * @param params
	 * @param callback
	 */
	public void postFormAsyn(String url, Map<String, Object> params, Callback callback) { 
		FormBody.Builder builder = new FormBody.Builder();
		for (Entry<String, Object> entry : params.entrySet())
			builder.add(entry.getKey(), entry.getValue().toString());
		RequestBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		client.newCall(request).enqueue(callback);;
	}
}
