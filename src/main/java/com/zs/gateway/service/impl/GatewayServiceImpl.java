/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.service.impl;

import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.service.GatewayService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 17:30
 */
@Log4j2
@Service("gatewayService")
public class GatewayServiceImpl implements GatewayService {
	
	@Autowired
	ChainBase chainBase;
	
	@Override
	public String processRequest(RequestVO requestVO) {
		try {
			chainBase.execute(requestVO);
		} catch (Exception e) {
			log.warn("error in chain!");
		}
		return "suc";
	}
}
