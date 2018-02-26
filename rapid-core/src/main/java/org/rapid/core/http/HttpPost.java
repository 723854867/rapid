package org.rapid.core.http;

import java.util.Map.Entry;

import org.rapid.core.bean.enums.ContentType;
import org.rapid.core.serialize.Serializer;
import org.rapid.util.Consts;

import com.google.gson.annotations.Expose;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class HttpPost<RESPONSE extends HttpResponse> extends HttpRequest<RESPONSE> {

	private static final long serialVersionUID = 328644647068283663L;
	
	@Expose(serialize = false, deserialize = false)
	private ContentType contentType;
	
	/**
	 * 默认使用表单
	 */
	public HttpPost(String prefix, String url, Serializer serializer, Serializer deserializer) {
		this(prefix, url, ContentType.APPLICATION_FORM_URLENCODED_UTF_8, serializer, deserializer);
	}
	
	public HttpPost(String prefix, String url, ContentType contentType, Serializer serializer, Serializer deserializer) {
		super(prefix, url, serializer, deserializer);
		this.contentType = contentType;
	}

	public ContentType contentType() {
		return contentType;
	}
	
	/**
	 * post方式需要填充 request body
	 */
	@Override
	protected Request request() {
		Request.Builder rb = new Request.Builder().url(url);
		for (Entry<String, String> entry : headers.entrySet())
			rb.addHeader(entry.getKey(), entry.getValue());
		return rb.post(_requestBody()).build();
	}
	
	/**
	 * 如果是表单则不需要实现 serial方法，否则需要实现 serial方法将 body 序列化为字节数组
	 */
	private RequestBody _requestBody() {
		switch (contentType) {
		case APPLICATION_FORM_URLENCODED_UTF_8:
			FormBody.Builder fb = new FormBody.Builder(Consts.UTF_8);
			for (Entry<String, String> entry : params.entrySet())
				fb.add(entry.getKey(), entry.getValue());
			return fb.build();
		default:
			return RequestBody.create(MediaType.parse(contentType.mark()), serial());
		}
	}
	
	protected byte[] serial() {
		throw new UnsupportedOperationException("Sub class must implements the serial method!");
	}
}
