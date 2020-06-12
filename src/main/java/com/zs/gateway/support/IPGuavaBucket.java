/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.support;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 13:53
 */
@Log4j2
public class IPGuavaBucket {
	
	/**
	 * 桶大小1000
	 */
	private final static int BUCKET_LIMIT = 1000;
	/**
	 * 10d的意思是一秒钟执行10次,不论多少个线程
	 */
	private final RateLimiter rateLimiter = RateLimiter.create(10d);
	/**
	 * 桶容器
	 */
	private final ConcurrentLinkedQueue<String> container = new ConcurrentLinkedQueue<>();
	
	private final Monitor producerMonitor = new Monitor();
	private final Monitor consumerMonitor = new Monitor();
	
	public void submit(String ip) {
		if (producerMonitor.enterIf(producerMonitor.newGuard(() -> container.size() < BUCKET_LIMIT))) {
			try {
				container.offer(ip);
				log.info(currentThread() + " submit ip " + ip + ",current size:" + container.size());
			} finally {
				producerMonitor.leave();
			}
		} else {
			//这里时候采用降级策略了。消费速度跟不上产生速度时，而且桶满了，抛出异常
			//或者存入MQ DB等后续处理
			throw new IllegalStateException("The bucket is full.");
		}
	}
	
	/**
	 * consumer这个类是消费者的抽象，可以通过accept消费消息。子类通过实现该接口自定义accept操作.
	 *
	 * @param consumer
	 */
	public void take(Consumer<String> consumer) {
		if (consumerMonitor.enterIf(consumerMonitor.newGuard(() -> !container.isEmpty()))) {
			try {
				rateLimiter.acquire();
				String ip = container.poll();
				consumer.accept(ip);
			} finally {
				consumerMonitor.leave();
			}
		} else {
			//当木桶的消费完后，可以消费那些降级存入MQ或者DB里面的数据
			System.out.println("will consumer Data from MQ...");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final IPGuavaBucket bucket = new IPGuavaBucket();
		final AtomicInteger DATA_CREATOR = new AtomicInteger(0);
		
		//生产线程 10个线程 每秒提交 50个数据  1/0.2s*10=50个
		IntStream.range(0, 10).forEach(i -> {
			new Thread(() -> {
				for (; ; ) {
					String ip = DATA_CREATOR.incrementAndGet() + "";
					try {
						bucket.submit(ip);
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (Exception e) {
						//对submit时，如果桶满了可能会抛出异常
						if (e instanceof IllegalStateException) {
							System.out.println(e.getMessage());
							//当满了后，生产线程就休眠1分钟
							try {
								TimeUnit.SECONDS.sleep(60);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}).start();
		});
		
		//消费线程  采用RateLimiter每秒处理10个  综合的比率是5:1
		IntStream.range(0, 10).forEach(i -> {
			new Thread(
					() -> {
						for (; ; ) {
							bucket.take(x -> {
								System.out.println(currentThread() + "C.." + x);
							});
						}
					}
			).start();
		});
	}
	
	
}
