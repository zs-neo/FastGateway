/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.chains;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zs.gateway.bean.Result;
import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.utils.RSAUtils;
import lombok.extern.log4j.Log4j2;

import static com.zs.gateway.chains.SignatureCheckHandler.keyMap;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 21:58
 */
@Log4j2
public class EncodeResponseHandler extends Handler {
	
	@Override
	public boolean execute(RequestVO requestVO) {
		log.info("step 7 : encode response...");
		String data = requestVO.getData();
		
		JSONObject jsonDataObj = JSONObject.parseObject(data);
		requestVO.setCode((int) jsonDataObj.get("code"));
		requestVO.setMsg((String) jsonDataObj.get("msg"));
		try {
			String bodyData = JSON.toJSONString(jsonDataObj.get("data"));
			String dataSign = RSAUtils.encryptBASE64(bodyData.getBytes());
			
			String privateKey = RSAUtils.getPrivateKey(keyMap);
			String rsaData = new String(RSAUtils.encryptByPrivateKey(dataSign.getBytes(), privateKey));
			
			requestVO.setData(rsaData);
			requestVO.setSign(dataSign);
			log.info("rsa data: " + rsaData);
			log.info("data sign: " + dataSign);
		} catch (Exception e) {
			log.warn("encode response error");
			e.printStackTrace();
			return true;
		}
		return false;
	}
}
