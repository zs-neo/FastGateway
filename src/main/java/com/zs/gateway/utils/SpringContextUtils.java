/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 安全的获取spring容器
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 11:43
 */
public class SpringContextUtils implements ApplicationContextAware {
	
	/**
	 * Spring应用上下文环境
	 */
	private static ApplicationContext applicationContext;
	
	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 *
	 * @param applicationContext 重写ApplicationContextAware中的setApplicationContext
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.setApp(applicationContext);
	}
	
	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * 为了规避findbugs的Write to static field from instance method潜在bug
	 * setApplicationContext实例化SpringContextUtil通过该方法为静态变量applicationContext赋值。
	 *
	 * @param applicationContext 上下文
	 */
	public static void setApp(ApplicationContext applicationContext) {
		SpringContextUtils.applicationContext = applicationContext;
	}
	
	/**
	 * 根据类名获取类对象
	 *
	 * @param name 类名
	 * @param <T>  对象
	 * @return 返回对象
	 * @throws BeansException 抛出获取类对象异常
	 */
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 根据class对象获取bean
	 *
	 * @param clazz class信息
	 * @param <T>   对象
	 * @return 返回类对象
	 */
	public static <T> T getBeanOfType(Class<T> clazz) {
		return (T) applicationContext.getBeansOfType(clazz);
	}
	
	/**
	 * 返回依赖注入的对象工程
	 * 对于想要拥有自动装配能力，并且想把这种能力暴露给外部应用的BeanFactory类需要实现此接口
	 *
	 * @return AutowireCapableBeanFactory
	 */
	public static AutowireCapableBeanFactory getAutowire() {
		return applicationContext.getAutowireCapableBeanFactory();
	}
}
