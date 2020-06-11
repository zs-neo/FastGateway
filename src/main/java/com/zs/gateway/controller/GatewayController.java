/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.controller;

import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.service.GatewayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/8 20:13
 */
@RestController
@Log4j2
public class GatewayController {
	
	@Autowired
	private GatewayService gatewayService;
	
	/**
	 * 代理请求,网关入口
	 * 例如通用请求格式/v1/orderSyetem/order/add?sig=123 body{123123}
	 *
	 * @param version       版本
	 * @param systemName    要调用的系统名称
	 * @param interfaceName 接口名称
	 * @param methodName    方法名
	 * @param request       请求
	 * @return
	 */
	@RequestMapping(value = "/{version}/{biz}/{interfaceName}/{methodName}/**", method = RequestMethod.POST)
	public String proxyRequest(@PathVariable("version") String version,
							   @PathVariable("biz") String systemName,
							   @PathVariable("interfaceName") String interfaceName,
							   @PathVariable("methodName") String methodName,
							   @RequestParam("sig") String signature,
							   @RequestBody String param,
							   HttpServletRequest request) {
		log.info("接收到请求, urlPath: {}", request.getServletPath());
		log.info("request version: {}, sys: {}, api: {}, method: {}, sig: {}, param: {}",
				version, systemName, interfaceName, methodName, signature, param);
		RequestVO requestVO = new RequestVO();
		return gatewayService.processRequest(requestVO);
	}
	
}