/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.service;

import com.zs.gateway.bean.vo.RequestVO;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 17:30
 */
public interface GatewayService {
	
	String processRequest(RequestVO requestVO);
	
}
