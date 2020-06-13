/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.support;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/13 20:40
 */
@Component
public class DubboGenericInvoke {
	
	private Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new ConcurrentHashMap<>();
	
	@Resource
	private ApplicationConfig application;
	
	@Resource
	private RegistryConfig registry;
	
	public Object invokeDubbo(String interfaceClass, String methodName, String[] invokeParamTypes, Object[] invokeParams) {
		ReferenceConfigCache cache = null;
		ReferenceConfig<GenericService> referenceConfig = null;
		try {
			referenceConfig = referenceConfigMap.get(interfaceClass);
			if (referenceConfig == null) {
				referenceConfig = createReferenceConfig(interfaceClass);
				referenceConfigMap.put(interfaceClass, referenceConfig);
			}
			cache = ReferenceConfigCache.getCache();
			GenericService genericService = cache.get(referenceConfig);
			return genericService.$invoke(methodName, invokeParamTypes, invokeParams);
		} catch (IllegalStateException e) {
			referenceConfigMap.remove(interfaceClass);
			if (cache != null) {
				cache.destroy(referenceConfig);
			}
			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ReferenceConfig<GenericService> createReferenceConfig(String interfaceClass) {
		ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
		referenceConfig.setApplication(application);
		referenceConfig.setRegistry(registry);
		referenceConfig.setConsumer(getConsumerConfig());
		referenceConfig.setGeneric(true);
		referenceConfig.setInterface(interfaceClass);
		return referenceConfig;
	}
	
	private ConsumerConfig getConsumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setTimeout(5000);
		consumerConfig.setRetries(2);
		return consumerConfig;
	}
	
}
