/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.example.demo.config;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/13 18:09
 */
@Configuration
public class DubboProviderConfig extends DubboConfig {
	
	@Bean
	public ProtocolConfig protocol() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setPort(20880);
		return protocolConfig;
	}
	
	@Bean
	public ProviderConfig provider() {
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setMonitor(monitorConfig());
		return providerConfig;
	}
	
	@Bean
	public ServiceBean<UserService> orderServiceBean(UserService userService) {
		final ServiceBean<UserService> serviceBean = new ServiceBean<>();
		serviceBean.setInterface(UserService.class);
		serviceBean.setRef(userService);
		serviceBean.setTimeout(10000);
		serviceBean.setRetries(0);
		return serviceBean;
	}
	
}