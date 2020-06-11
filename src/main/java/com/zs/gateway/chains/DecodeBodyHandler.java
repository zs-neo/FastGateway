/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.chains;

import com.zs.gateway.bean.vo.RequestVO;
import lombok.extern.log4j.Log4j2;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 16:59
 */
@Log4j2
public class DecodeBodyHandler extends Handler{
	
	@Override
	public boolean execute(RequestVO requestVO) {
		log.info("body decoding...");
		return false;
	}
}

