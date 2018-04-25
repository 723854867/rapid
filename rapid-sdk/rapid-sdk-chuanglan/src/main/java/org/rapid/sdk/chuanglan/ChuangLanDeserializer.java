package org.rapid.sdk.chuanglan;

import java.lang.reflect.Type;

import org.rapid.sdk.chuanglan.response.SendSmsResponse;
import org.rapid.util.serialize.Serializer;

@SuppressWarnings("unchecked")
public class ChuangLanDeserializer implements Serializer {
	
	public static final ChuangLanDeserializer INSTANCE = new ChuangLanDeserializer();
	
	@Override
	public byte[] serial(Object object) {
		return null;
	}

	@Override
	public <ENTITY> ENTITY deserial(byte[] buffer, Type type) {
		String content = new String(buffer);
		return (ENTITY) _response(content);
	}

	@Override
	public <ENTITY> ENTITY deserial(byte[] buffer, Class<ENTITY> clazz) {
		String content = new String(buffer);
		return (ENTITY) _response(content);
	}
	
	private SendSmsResponse _response(String content) {
		String[] arr = content.split("\\n");
		String[] arr1 = arr[0].split(",");
		SendSmsResponse response = new SendSmsResponse();
		response.setCode(arr1[1]);
		response.setTime(arr1[0]);
		response.setMsgId(arr[1]);
		return response;
	}
}
