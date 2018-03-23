package org.rapid.sdk.sina;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	public static final boolean verify(SinaNotice notice, String pubKey) {
			String sign_result = notice.getSign().toString();
			Map<String, Object> map = BeanUtil.beanToTreeMap(notice, false);
			map.remove("sign");
			map.remove("sign_type");
			map.remove("sign_version");
			// 去除map中为空参数
			Iterator<Map.Entry<String, Object>> it2 = map.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry<String, Object> entry2 = it2.next();
				String value2 = entry2.getValue().toString();
				if (value2 == null || value2 == "" || value2.equals("")) {
					it2.remove();
				}
			}
			String like_result = trimInnerSpaceStr(createLinkString(map, false));
			return Decrypt.RSASignVerify(like_result, Base64.decodeBase64(sign_result), pubKey, SignatureAlgorithm.SHA1withRSA);
	}
	public static String trimInnerSpaceStr(String str) {
		str = str.trim();
		while (str.startsWith(" ")) {
			str = str.substring(1, str.length()).trim();
		}
		while (str.endsWith(" ")) {
			str = str.substring(0, str.length() - 1).trim();
		}

		return str;
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

	public static String createLinkString(Map<String, Object> params, boolean encode) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		String charset = params.get("_input_charset").toString();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key).toString();
			if (encode) {
				try {
					value = URLEncoder.encode(URLEncoder.encode(value, charset), charset);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

}
