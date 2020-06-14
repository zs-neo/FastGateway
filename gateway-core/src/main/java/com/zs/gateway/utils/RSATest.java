/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.utils;

import java.security.Key;
import java.util.Map;


/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/14 11:00
 */
public class RSATest {
	
	private String publicKey;
	private String privateKey;
	
	public void setUp() throws Exception {
		Map<String, Key> keyMap = RSAUtils.initKey();
		publicKey = RSAUtils.getPublicKey(keyMap);
		privateKey = RSAUtils.getPrivateKey(keyMap);
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥： \n\r" + privateKey);
	}
	
	public void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String inputStr = "{\"code\": \"123123\",\"username\": \"tom\",\"password\": \"123456\"}";
		byte[] encodedData = RSAUtils.encryptByPublicKey(inputStr, publicKey);
		System.out.println(new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
				privateKey);
		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
	}
	
	public void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String inputStr = "dounine";
		byte[] data = inputStr.getBytes();
		byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = RSAUtils.sign(encodedData, privateKey);
		System.err.println("签名:" + sign);
		// 验证签名
		boolean status = RSAUtils.verify(encodedData, publicKey, sign);
		System.err.println("状态:" + status);
	}
	
	public static void main(String[] args) {
		RSATest rsaTest = new RSATest();
		try{
			rsaTest.setUp();
			rsaTest.test();
			rsaTest.testSign();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}