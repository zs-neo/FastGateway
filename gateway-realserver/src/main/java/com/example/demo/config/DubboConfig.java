/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.example.demo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/13 18:05
 */
public class DubboConfig {
	
	@Value("${application.name}")
	private String applicationName;
	
	@Bean
	public RegistryConfig registry() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress("127.0.0.1:2182");
		registryConfig.setProtocol("zookeeper");
		return registryConfig;
	}
	
	
	@Bean
	public ApplicationConfig application() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(applicationName);
		return applicationConfig;
	}
	
	@Bean
	public MonitorConfig monitorConfig() {
		MonitorConfig mc = new MonitorConfig();
		mc.setProtocol("registry");
		return mc;
	}
	
}
