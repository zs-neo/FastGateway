/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.support;

import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 计数器算法实现ip限流
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 11:56
 */
@Getter
public class IPCounter {
	
	/**
	 * 超时时间规则
	 */
	private final static long timeGapRule = 1000;
	/**
	 * ip计数规则
	 */
	private final static int countRule = 3;
	/**
	 * ip:ip的访问次数
	 */
	private ConcurrentHashMap<String, Integer> counterMap = new ConcurrentHashMap<>();
	/**
	 * ip:ip上次的访问时间
	 */
	private ConcurrentHashMap<String, Long> timeMap = new ConcurrentHashMap<>();
	
	public IPCounter() {
	}
	
	public boolean allow(String ip) {
		Long lastTime = timeMap.get(ip);
		Long nowTime = System.currentTimeMillis();
		// 第一次记录ip或计时器超时就重置
		if (lastTime == null || (nowTime - lastTime) > timeGapRule) {
			timeMap.put(ip, nowTime);
			counterMap.put(ip, 0);
		}
		Integer count;
		if ((count = counterMap.get(ip)) != null) {
			count++;
			counterMap.put(ip, count);
		}
		return count <= countRule;
	}
	
	public static void main(String[] args) {
		// test: 1s for 3 max tries
		IPCounter counter = new IPCounter();
		String ip = "192.168.1.1";
		while (true) {
			if (counter.allow(ip)) {
				System.out.println(true);
			}
		}
	}
	
}
