/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.config;

import com.zs.gateway.chains.*;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 责任链模式
 * 接口限流->黑名单->白名单->数字签名验证->解码body数据->检查api信息->检查api参数->远程调用对应的接口
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 16:38
 */
@Configuration
public class ChainConfig {
	
	@Bean
	Command interfaceFlowLimitHandler() {
		return new InterfaceFlowLimitHandler();
	}
	
	@Bean
	Command blackIpListCheckHandler() {
		return new BlackIpListCheckHandler();
	}
	
	@Bean
	Command whiteIpListCheckHandler() {
		return new WhiteIpListCheckHandler();
	}
	
	@Bean
	Command signatureCheckHandler() {
		return new SignatureCheckHandler();
	}
	
	@Bean
	Command decodeBodyHandler() {
		return new DecodeBodyHandler();
	}
	
	@Bean
	Command apiInfoCheckHandler() {
		return new ApiInfoCheckHandler();
	}
	
	@Bean
	Command apiParamCheckHandler() {
		return new ApiParamCheckHandler();
	}
	
	@Bean
	Command invokeInterfaceHandler() {
		return new InvokeInterfaceHandler();
	}
	
	@Bean
	ChainBase chains() {
		ChainBase chainBase = new ChainBase();
		chainBase.addCommand(interfaceFlowLimitHandler());
		chainBase.addCommand(blackIpListCheckHandler());
		chainBase.addCommand(whiteIpListCheckHandler());
		chainBase.addCommand(signatureCheckHandler());
		chainBase.addCommand(decodeBodyHandler());
		chainBase.addCommand(apiInfoCheckHandler());
		chainBase.addCommand(apiParamCheckHandler());
		chainBase.addCommand(invokeInterfaceHandler());
		return chainBase;
	}
	
	
}
