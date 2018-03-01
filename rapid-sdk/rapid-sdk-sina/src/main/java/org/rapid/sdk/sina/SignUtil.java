package org.rapid.sdk.sina;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.rapid.util.Consts.Symbol;
import org.rapid.util.codec.CryptConsts.SignatureAlgorithm;
import org.rapid.util.codec.CryptConsts.Transformation;
import org.rapid.util.codec.Encrypt;

public class SignUtil {
	
	private static final Set<String> ENCRYPT_FIELDS = new HashSet<String>() {
		private static final long serialVersionUID = -6522560067080004018L;
		{
			add("cert_no");
			add("phone_no");
			add("real_name");
			add("account_name");
			add("validity_period");
			add("bank_account_no");
			add("verification_value");
		}
	};
	
	/**
	 * 签名
	 */
	public static final String sign(Map<String, String> params, String priKey) {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : params.entrySet()) {
			if (entry.getKey().equals("sign") || entry.getKey().equals("sign_type") || entry.getKey().equals("sign_version"))
				continue;
			builder.append(entry.getKey()).append(Symbol.EQUAL).append(entry.getValue()).append("&");
		}
		builder.deleteCharAt(builder.length() - 1);
		byte[] sinData = Encrypt.RSASign(builder.toString().getBytes(), priKey, SignatureAlgorithm.SHA1withRSA);
		return Base64.encodeBase64String(sinData);
	}
	
	/**
	 * 敏感字段加密
	 */
	public static final void encrypt(Map<String, String> params, String pubKey) {
		for (Entry<String, String> entry : params.entrySet()) {
			if (!ENCRYPT_FIELDS.contains(entry.getKey())) 
				continue;
			byte[] data = entry.getValue().getBytes();
			byte[] encryptData = Encrypt.RSAEncodeByPublicKey(data, pubKey, Transformation.RSA);
			entry.setValue(Base64.encodeBase64String(encryptData));
		}
	}
}
