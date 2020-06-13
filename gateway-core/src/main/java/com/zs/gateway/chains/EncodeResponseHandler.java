/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.chains;

import com.zs.gateway.bean.Result;
import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.utils.RSAUtils;
import lombok.extern.log4j.Log4j2;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 21:58
 */
@Log4j2
public class EncodeResponseHandler extends Handler {
	
	@Override
	public boolean execute(RequestVO requestVO) {
		String data = requestVO.getData();
		try {
			String dataSign = RSAUtils.encryptBASE64(data.getBytes());
			data = RSAUtils.encryptByPrivateKey(dataSign.getBytes()).toString();
			requestVO.setData(data);
		} catch (Exception e) {
			log.warn("encode response error");
			e.printStackTrace();
			return true;
		}
		log.info("encode response...");
		return false;
	}
}
