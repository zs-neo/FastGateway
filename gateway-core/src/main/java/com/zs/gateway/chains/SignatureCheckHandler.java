/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.chains;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.utils.RSAUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 16:56
 */
@Log4j2
public class SignatureCheckHandler extends Handler {
	
	public static Map<String, Key> keyMap;
	
	private static String publicKey;
	private static String privateKey;
	
	public SignatureCheckHandler() {
		try {
			if (StringUtils.isEmpty(publicKey) || StringUtils.isEmpty(privateKey)) {
				synchronized (this) {
					keyMap = RSAUtils.initKey();
					publicKey = RSAUtils.getPublicKey(keyMap);
					privateKey = RSAUtils.getPrivateKey(keyMap);
				}
			}
			log.info("公钥: \n\r" + publicKey);
			log.info("私钥： \n\r" + privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接口防刷，保证接口安全，但因为有sign的存在不保证数据安全。。
	 * 1.客户端的请求的json串格式类似{"name":"taoeyhuang","age":18}
	 * json直接通过base64加密，得到数字摘要sign(此处参数+secret后再做摘要处理可保证数据安全，但目前不做实现)
	 * sign通过公钥加密得到加密data
	 * data放到post请求的body中，sign放到post请求的请求头里
	 * <p>
	 * 2.后端接收到请求后，先通过私钥解密data得到reqSign
	 * 比较reqSign和sign是否一致
	 * - 不一致 返回失败
	 * - 一致 base64解密reqSign得到数据，组装参数
	 *
	 * @param requestVO
	 * @return
	 */
	@Override
	public boolean execute(RequestVO requestVO) {
		log.info("step 4 : signature checking...");
		String data = requestVO.getRequestParams().get("param");
		String sign = requestVO.getRequestParams().get("signature");
		log.info("签名: " + sign);
		log.info("加密body数据: " + data);
		JSONObject requestBodyData;
		try {
			// RSA私钥解密
			byte[] reqSign = RSAUtils.decryptByPrivateKey(data, RSAUtils.getPrivateKey(keyMap));
			if (reqSign == null) {
				log.warn("request sign of param is null");
				return true;
			}
			// 验签
			log.info("sign: " + sign);
			log.info("reSi: " + new String(reqSign));
			if (sign.equals(new String(reqSign))) {
				// base64解密
				String reqSingnStr = new String(Base64.decodeBase64(reqSign));
				log.info("request decode base64: " + reqSingnStr);
				// 提取参数
				Map<String, String> bodyParams = new HashMap<>();
				requestBodyData = JSON.parseObject(reqSingnStr);
				Iterator<String> it = requestBodyData.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					String value = requestBodyData.getString(key);
					bodyParams.put(key, value);
				}
				requestVO.setDataParams(bodyParams);
				log.info("request body: " + JSON.toJSONString(bodyParams));
				return false;
			} else {
				log.warn("request sign of param error dangerously");
				return true;
			}
		} catch (Exception e) {
			log.warn("request sign decrypt error");
			e.printStackTrace();
		}
		return false;
	}
}
