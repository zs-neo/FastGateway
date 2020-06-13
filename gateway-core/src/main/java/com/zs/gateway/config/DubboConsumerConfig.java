/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.config;

import com.alibaba.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/13 21:23
 */
@Configuration
public class DubboConsumerConfig extends DubboConfig {
	
	private ConsumerConfig getConsumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setTimeout(5000);
		consumerConfig.setRetries(2);
		return consumerConfig;
	}
	
}
