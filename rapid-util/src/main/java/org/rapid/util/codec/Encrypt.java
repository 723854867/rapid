package org.rapid.util.codec;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.rapid.util.Consts;
import org.rapid.util.codec.CryptConsts.SignatureAlgorithm;
import org.rapid.util.codec.CryptConsts.Transformation;
import org.rapid.util.exception.CryptException;

public class Encrypt {
	
	/**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
	/**
	 * AES对称加密
	 * 
	 * @param aesKey
	 * @param content
	 * @return
	 */
	public static String AESEncode(String aesKey, String content) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			keygen.init(128, new SecureRandom(aesKey.getBytes()));
			SecretKey original_key = keygen.generateKey();
			byte[] raw = original_key.getEncoded();
			SecretKey key = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptData = cipher.doFinal(content.getBytes(Consts.UTF_8));
			return Base64.encodeBase64String(encryptData);
		} catch (Exception e) {
			throw new CryptException("AES加密失败", e);
		}
	}
	
	/**
	 * RSA 签名
	 * 
	 * @param data
	 * @param privateKey
	 */
	public static byte[] RSASign(byte[] data, String privateKey, SignatureAlgorithm algorithm) {
        try {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Signature signature = Signature.getInstance(algorithm.name());
			signature.initSign(privateK);
			signature.update(data);
			return signature.sign();
		} catch (Exception e) {
			throw new CryptException("RSA签名失败", e);
		} 
    }
	
	/**
	 * RSA 非对称加密
	 * 
	 * @param content
	 * @param publicKey
	 * @return
	 */
	public static byte[] RSAEncodeByPublicKey(byte[] data, String publicKey, Transformation transformation) {
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key key = keyFactory.generatePublic(keySpec);
			Cipher cipher = Cipher.getInstance(transformation.mark());
			cipher.init(Cipher.ENCRYPT_MODE, key);
	        int i = 0;
	        byte[] cache;
	        int offSet = 0;
	        int inputLen = data.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) 
	                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	            else 
	                cache = cipher.doFinal(data, offSet, inputLen - offSet);
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_ENCRYPT_BLOCK;
	        }
	        byte[] encryptData = out.toByteArray();
	        out.close();
			return encryptData;
		} catch (Exception e) {
			throw new CryptException("RSA加密失败", e);
		}
    }
	
	public static void main(String[] args) {
		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCceKqsJDtRs4Ahf69ZPpCbEkMn61zKpt1cI4GXszW/OXyQJ4XVo2ptjfIwMfArQrIAPfRvjaWKKxfXo2BfELZwwghPSi9C5fJAdUmOvXxh9Y25A80t8FrQ2AgqOnNoPGeng3HmztamX5qTkY9YMoXM06aP/ovoyZ6DOKczybM30QIDAQAB";
		String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJx4qqwkO1GzgCF/r1k+kJsSQyfrXMqm3VwjgZezNb85fJAnhdWjam2N8jAx8CtCsgA99G+NpYorF9ejYF8QtnDCCE9KL0Ll8kB1SY69fGH1jbkDzS3wWtDYCCo6c2g8Z6eDcebO1qZfmpORj1gyhczTpo/+i+jJnoM4pzPJszfRAgMBAAECgYBZ+BwSvQaKtM6g9F0r0IivUbHhmziQoZ+4YgYa5wWpfXF9JeyTadJBShQO2DgDd+cHspgBKI4dwyeDa0J3hxGPHy9OVXh36n4PkWFA27fV7Gl7hB1MXS4bD0Xgf/fR/IxRBdwIZXRupTLAVgLNBXY/vPHBHm5BhjKWzeiQXRjhXQJBANtuijbD54W+/zqdYL0HpIJvCzH8tPJw9LPw7DhYDvHKJjeJoTSTEUlLk+9qpE/PQdrHkY3PpJQ/Pc2xp2cmvoMCQQC2jBdJIxP/ILp9jJTZV05hfqUIEZCYl4OctAm/LMm99BdgrLmZOcrQEVgDBlXgCpdCo3SCqTRC0pIXw2dSZGAbAkEApfNnnBt00XWtWQsEynEpPWv1r4xK3MAIkL24KLSQONSFKqNvw28mN6Rs8ThZo/I+/9LGhdVvxp49g37D+AMZOQJBAK2GedQiLi4FlIEoE46OuYlf88y5tbP3wzpAwksibhvNXjOdvUf0ceBbvEgTy6oPJz5HVselxHII7Kr+KsdddyUCQFM2CaQPpcTRGgNmwPOhKMPTRCQOCUim6U2ikE9v4lPmkupbjcnaA/tZD25ILj2qOMMO0z7MgPnmhLGkhc/iQE8=";
		String content = "RPC 协议扩展，封装远程调用细节。\r\n" + 
				"\r\n" + 
				"契约：\r\n" + 
				"\r\n" + 
				"当用户调用 refer() 所返回的 Invoker 对象的 invoke() 方法时，协议需相应执行同 URL 远端 export() 传入的 Invoker 对象的 invoke() 方法。\r\n" + 
				"其中，refer() 返回的 Invoker 由协议实现，协议通常需要在此 Invoker 中发送远程请求，export() 传入的 Invoker 由框架实现并传入，协议不需要关心。\r\n" + 
				"注意：\r\n" + 
				"\r\n" + 
				"协议不关心业务接口的透明代理，以 Invoker 为中心，由外层将 Invoker 转换为业务接口。\r\n" + 
				"协议不一定要是 TCP 网络通讯，比如通过共享文件，IPC 进程间通讯等。";
		byte[] signData = RSASign(content.getBytes(), priKey, SignatureAlgorithm.SHA1withRSA);
		System.out.println(Base64.encodeBase64String(signData));
		byte[] encryptData = RSAEncodeByPublicKey(content.getBytes(), pubKey, Transformation.RSA);
		System.out.println(Base64.encodeBase64String(encryptData));
		byte[] decryptData = Decrypt.RSADecodeByPrivateKey(encryptData, priKey, Transformation.RSA);
		System.out.println(new String(decryptData));
	}
}
