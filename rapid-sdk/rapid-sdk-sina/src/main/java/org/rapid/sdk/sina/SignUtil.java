package org.rapid.sdk.sina;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.rapid.sdk.sina.notice.SinaNotice;
import org.rapid.util.Consts.Symbol;
import org.rapid.util.codec.CryptConsts.SignatureAlgorithm;
import org.rapid.util.codec.CryptConsts.Transformation;
import org.rapid.util.codec.Decrypt;
import org.rapid.util.codec.Encrypt;
import org.rapid.util.reflect.BeanUtil;

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
			add("license_no");
			add("telephone");
			add("email");
			add("organization_no");
			add("legal_person");
			add("cert_no");
			add("legal_person_phone");
			add("bank_account_no");
		}
	};

	public static String fileMD5(InputStream in) throws Exception {
		MessageDigest digest = null;
		byte buffer[] = new byte[1024];
		int len;
		digest = MessageDigest.getInstance("MD5");
		while ((len = in.read(buffer, 0, 1024)) != -1)
			digest.update(buffer, 0, len);
		in.close();
		return _bytes2hex03(digest.digest());
	}
	
	private static String _bytes2hex03(byte[] bytes) {
		final String HEX = "0123456789abcdef";
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			sb.append(HEX.charAt((b >> 4) & 0x0f));
			sb.append(HEX.charAt(b & 0x0f));
		}
		return sb.toString();
	}

	/**
	 * 签名
	 */
	public static final String sign(Map<String, String> params, String priKey) {
		byte[] sinData = Encrypt.RSASign(signStr(params).getBytes(), priKey, SignatureAlgorithm.SHA1withRSA);
		return Base64.encodeBase64String(sinData);
	}
	
	// 验签
	public static final boolean verify(SinaNotice notice) {
		Map<String, Object> map = BeanUtil.beanToTreeMap(notice, false);
		Map<String, String> params = new TreeMap<String, String>();
		Object sign = map.remove("sign");
		Object signType = map.remove("sign_type");
		Object signVersion = map.remove("sign_version");
		for (Entry<String, Object> entry : map.entrySet()) {
			try {
				String key = URLEncoder.encode(entry.getKey(), "UTF-8");
				String value = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
				params.put(key, value);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("新浪字段 urldecode 失败！");
			}
		}
		byte[] signData = Base64.decodeBase64(sign.toString());
		String signStr = SignUtil.signStr(params);
		return Decrypt.RSASignVerify(signStr, signData, SinaConfig.PUB_KEY.getDefaultValue(), SignatureAlgorithm.SHA1withRSA);
	}
	
	/**
	 * 待签名字符串
	 */
	public static final String signStr(Map<String, String> params) {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : params.entrySet()) {
			if (entry.getKey().equals("sign") || entry.getKey().equals("sign_type")
					|| entry.getKey().equals("sign_version"))
				continue;
			builder.append(entry.getKey()).append(Symbol.EQUAL).append(entry.getValue()).append("&");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
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
