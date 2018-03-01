package org.rapid.util.codec;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.rapid.util.Consts;
import org.rapid.util.codec.CryptConsts.Transformation;
import org.rapid.util.exception.CryptException;

public class Decrypt {
	
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * AES解密
	 * 
	 * @param aesKey
	 * @param content
	 */
	public static String AESDecode(String aesKey, String content) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			keygen.init(128, new SecureRandom(aesKey.getBytes()));
			SecretKey originalKey = keygen.generateKey();
			byte[] raw = originalKey.getEncoded();
			SecretKey key = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] buffer = Base64.decodeBase64(content);
			buffer = cipher.doFinal(buffer);
			return new String(buffer, Consts.UTF_8);
		} catch (Exception e) {
			throw new CryptException("AES解密失败:" + e.getMessage(), e);
		}
	}
	
	/**
	 * RSA 私钥解密
	 * 
	 * @param data
	 * @param privateKey
	 */
	public static byte[] RSADecodeByPrivateKey(byte[] encryptData, String privateKey, Transformation transformation) {
		try {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(transformation.mark());
			cipher.init(Cipher.DECRYPT_MODE, privateK);
			int i = 0;
			byte[] cache;
			int offSet = 0;
			int inputLen = encryptData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while (inputLen > offSet) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) 
					cache = cipher.doFinal(encryptData, offSet, MAX_DECRYPT_BLOCK);
				else 
					cache = cipher.doFinal(encryptData, offSet, inputLen - offSet);
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] data = out.toByteArray();
			out.close();
			return data;
		} catch (Exception e) {
			throw new CryptException("RSA解密失败", e);
		}
	}
}
