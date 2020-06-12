/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.bean.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.chain.impl.ContextBase;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 15:11
 */
@Data
public class RequestVO extends ContextBase {
	
	private HttpServletRequest request;
	private String clientIP;
	private Map<String, String> requestParams;
	private Map<String, String> dataParams;
	
	private Integer code;
	private String msg;
	private String data;
	private String sign;
	/**
	 * 是否被限流
	 */
	private Boolean limit;
	
}
