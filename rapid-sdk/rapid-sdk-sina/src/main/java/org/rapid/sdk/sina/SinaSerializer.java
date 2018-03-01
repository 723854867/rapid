package org.rapid.sdk.sina;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.serialize.GsonSerializer;
import org.rapid.core.serialize.SerializeUtil;

public class SinaSerializer extends GsonSerializer {

	public static final SinaSerializer INSTANCE = new SinaSerializer();

	public SinaSerializer() {}

	public SinaSerializer(boolean annotated) {
		super(annotated);
	}
	
	@Override
	public <ENTITY> ENTITY deserial(byte[] buffer, Class<ENTITY> clazz) {
		String value = new String(buffer, charset);
		try {
			value = value.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
			value = URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new BizException(Code.SYS_ERROR);
		}
		return annotated ? SerializeUtil.GSON_ANNO.fromJson(value, clazz) : SerializeUtil.GSON.fromJson(value, clazz);
	}
}
