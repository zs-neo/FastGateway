/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.support;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 令牌桶算法
 * - 相比漏桶算法而言区别在于，令牌桶是会去匀速的生成令牌，拿到令牌才能够进行处理，类似于匀速往桶里放令牌
 * - 漏桶算法是：生产者消费者模型，生产者往木桶里生产数据，消费者按照定义的速度去消费数据
 * 应用场景：
 * -漏桶算法：必须读写分流的情况下，限制读取的速度
 * -令牌桶算法：必须读写分离的情况下，限制写的速率
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 14:35
 */
@Log4j2
@Component
public class IPGuavaTokenBucket {
	
	private final int limit;
	private final static int DEFALUT_LIMIT = 500;
	private RateLimiter rateLimiter = RateLimiter.create(100d);
	
	public IPGuavaTokenBucket() {
		this(DEFALUT_LIMIT);
	}
	
	public IPGuavaTokenBucket(int limit) {
		this.limit = limit;
	}
	
	/**
	 * 10秒内没有抢到就抛出异常
	 *
	 * @return
	 */
	public boolean allow() {
		// 设置10s的超时时间
		boolean success = rateLimiter.tryAcquire(10, TimeUnit.SECONDS);
		if (success) {
			return true;
		} else {
			// 超时后同一时间，大流量来时，超时快速失败
//			throw new RuntimeException(currentThread() + "has timeOut can try again...");
		}
		return false;
	}
	
	public static void main(String[] args) {
		final IPGuavaTokenBucket tokenBuck = new IPGuavaTokenBucket(20);
		
		IntStream.range(0, 10).forEach(i -> {
			//目前测试时，让一个线程抢一次，不用循环抢
			new Thread(tokenBuck::allow).start();
		});
		
	}
	
}