/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import com.zs.gateway.bean.Result;
import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.enums.CodeMsg;
import com.zs.gateway.service.GatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.zs.gateway.enums.Constant.*;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/8 20:13
 */
@Api(value = "GatewayController", tags = {"gateway网关入口"})
@Log4j2
@RestController
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
	@ApiOperation(value = "网关请求入口", notes = "网关请求入口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "version", value = "接口版本", required = false, dataType = "String"),
			@ApiImplicitParam(name = "biz", value = "系统名称", required = false, dataType = "String"),
			@ApiImplicitParam(name = "interfaceName", value = "接口名称", required = false, dataType = "String"),
			@ApiImplicitParam(name = "methodName", value = "方法名称", required = false, dataType = "String"),
			@ApiImplicitParam(name = "sig", value = "数字签名", required = false, dataType = "String")
	})
	@RequestMapping(value = "/{version}/{biz}/{interfaceName}/{methodName}/**", method = RequestMethod.POST)
	public Result<String> proxyRequest(@PathVariable("version") String version,
									   @PathVariable("biz") String systemName,
									   @PathVariable("interfaceName") String interfaceName,
									   @PathVariable("methodName") String methodName,
									   @RequestBody String param,
									   HttpServletRequest request) {
		JSONObject jsonObject = JSONObject.parseObject(param);
		log.info("接收到请求, urlPath: {}", request.getServletPath());
		log.info("request version: {}, sys: {}, api: {}, method: {}, sig: {}, param: {}",
				version, systemName, interfaceName, methodName,jsonObject.getString("sign"), jsonObject.getString("data"));
		
		RequestVO requestVO = new RequestVO();
		
		Map<String, String> params = new HashMap<>();
		params.put(VERSION, version);
		params.put(SYS, systemName);
		params.put(INTERFACENAME, interfaceName);
		params.put(METHODNAME, methodName);
		params.put(SIGNATURE, jsonObject.getString("sign"));
		params.put(PARAM, jsonObject.getString("data"));
		params.put(URL, request.getServletPath());
		requestVO.setRequestParams(params);
		
		String clientIp = request.getRemoteHost();
		String xff = request.getHeader(X_FORWARDED_FOR);
		if (!StringUtils.isEmpty(xff)) {
			clientIp = xff.split(",")[0];
		}
		requestVO.setClientIP(clientIp);
		
		Result<String> result = gatewayService.processRequest(requestVO);
		if (result == null) {
			return Result.error(CodeMsg.SERVER_ERROR);
		}
		return result;
	}
	
}