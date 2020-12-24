package com.mhtech.platform.msrv.sharing.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

/**
 * RSA 签名算法 工具
 * 
 * @author GM
 *
 */
public abstract class RSAUtils {

	static final String ALGORITHM_TYPE = "RSA";
	static final String CHARSET = "UTF-8";
	static final int KEY_SIZE = 2048; // 即RSA2
	// 定义签名算法
	final static String KEY_RSA_SIGNATURE = "MD5withRSA";

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 245;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 256;

	private RSAUtils() {
	}

	/**
	 * 生成配对秘钥
	 * 
	 * @return
	 */
	public static PairKey getPairKey() {
		KeyPairGenerator generator;
		try {
			generator = KeyPairGenerator.getInstance(ALGORITHM_TYPE);
			generator.initialize(KEY_SIZE);
			KeyPair pair = generator.generateKeyPair();
			RSAPublicKey pk = (RSAPublicKey) pair.getPublic();
			RSAPrivateKey privKey = (RSAPrivateKey) pair.getPrivate();
			return new PairKey(Base64.getEncoder().encodeToString(
					pk.getEncoded()), Base64.getEncoder().encodeToString(
					privKey.getEncoded()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥加密 【隐藏 服务端禁止存储用户私钥 仅测试使用】
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String data, String privateKey)
			throws Exception {
		byte[] decoded = Base64.getDecoder().decode(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance(ALGORITHM_TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, priKey);
		byte[] dataByte = data.getBytes(CHARSET), cache;
		int dataLength = dataByte.length, offSet = 0, i = 0;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			// 数据分段加密
			while (dataLength - offSet > 0) {
				if (dataLength - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(dataByte, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(dataByte, offSet, dataLength
							- offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			return Base64.getEncoder().encodeToString(encryptedData);
		}
	}

	/**
	 * 私钥解密 【隐藏 服务端禁止存储用户私钥 仅测试使用】
	 * @param encryptedStr
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String encryptedStr,
			String privateKeyStr) throws Exception {
		// 对私钥解密
		byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr);
		// 获得私钥
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		// 获得待解密数据
		byte[] data = Base64.getDecoder().decode(encryptedStr);
		KeyFactory factory = KeyFactory.getInstance(ALGORITHM_TYPE);
		PrivateKey privateKey = factory.generatePrivate(keySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		// 返回UTF-8编码的解密信息
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, CHARSET);
	}

	/**
	 * 公钥加密
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String src, String publicKey)
			throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_TYPE);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		byte[] data = src.getBytes(CHARSET);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return Base64.getEncoder().encodeToString(encryptedData);
	}

	/**
	 * 公钥解密
	 * 
	 * @param encryptedData
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPublicKey(String encryptedStr,
			String publicKey) throws Exception {
		byte[] encryptedData = Base64.getDecoder().decode(encryptedStr);
		byte[] keyBytes = Base64.getDecoder().decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_TYPE);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher
						.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher
						.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, CHARSET);
	}

	public static String sign(String encryptedStr, String privateKey) {
		String str = "";
		try {
			// 将私钥加密数据字符串转换为字节数组
			byte[] data = encryptedStr.getBytes();
			// 解密由base64编码的私钥
			byte[] bytes = Base64.getDecoder().decode(privateKey);
			// 构造PKCS8EncodedKeySpec对象
			PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
			// 指定的加密算法
			KeyFactory factory = KeyFactory.getInstance(ALGORITHM_TYPE);
			// 取私钥对象
			PrivateKey key = factory.generatePrivate(pkcs);
			// 用私钥对信息生成数字签名
			Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
			signature.initSign(key);
			signature.update(data);
			str = Base64.getEncoder().encodeToString(signature.sign());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static boolean verify(String encryptedStr, String publicKey,
			String sign) {
		boolean flag = false;
		try {
			// 将私钥加密数据字符串转换为字节数组
			byte[] data = encryptedStr.getBytes();
			// 解密由base64编码的公钥
			byte[] bytes = Base64.getDecoder().decode(publicKey);
			// 构造X509EncodedKeySpec对象
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
			// 指定的加密算法
			KeyFactory factory = KeyFactory.getInstance(ALGORITHM_TYPE);
			// 取公钥对象
			PublicKey key = factory.generatePublic(keySpec);
			// 用公钥验证数字签名
			Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
			signature.initVerify(key);
			signature.update(data);
			flag = signature.verify(Base64.getDecoder().decode(sign));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static final class PairKey {
		private String publicKey;
		private String privateKey;
		
		static {
			System.err.println("PairKey init....");
		}
		
		public PairKey() {}
		
		public PairKey(String publicKey, String privateKey) {
			this.publicKey = publicKey;
			this.privateKey = privateKey;
		}
		public String getPublicKey() {
			return publicKey;
		}
		public void setPublicKey(String publicKey) {
			this.publicKey = publicKey;
		}
		public String getPrivateKey() {
			return privateKey;
		}
		public void setPrivateKey(String privateKey) {
			this.privateKey = privateKey;
		}
	}
}
