/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.enums;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 21:38
 */
public class Constant {
	
	public final static String VERSION = "version";
	public final static String SYS = "sys";
	public final static String INTERFACENAME = "interfaceName";
	public final static String METHODNAME = "methodName";
	public final static String SIGNATURE = "signature";
	public final static String PARAM = "param";
	public final static String URL = "url";
	/**
	 * 在Java中，获取客户端IP最直接的方式就是使用request.getRemoteAddr()。
	 * 这种方式能获取到连接服务器的客户端IP，在中间没有代理的情况下，的确是最简单有效的方式。
	 * 但是目前互联网Web应用很少会将应用服务器直接对外提供服务，一般都会有一层Nginx做反向代理和负载均衡，有的甚至可能有多层代理。
	 * 在有反向代理的情况下，直接使用request.getRemoteAddr()获取到的IP地址是Nginx所在服务器的IP地址，而不是客户端的IP。
	 * HTTP协议是基于TCP协议的，由于request.getRemoteAddr()默认获取到的是TCP层直接连接的客户端的IP.
	 * 对于Web应用服务器来说直接连接它的客户端实际上是Nginx，也就是TCP层是拿不到真实客户端的IP。
	 * 为了解决上面的问题，很多HTTP代理会在HTTP协议头中添加X-Forwarded-For头，用来追踪请求的来源。X-Forwarded-For的格式如下：
	 * X-Forwarded-For: client1, proxy1, proxy2
	 * X-Forwarded-For包含多个IP地址，每个值通过逗号+空格分开，最左边（client1）是最原始客户端的IP地址，中间如果有多层代理.
	 * 每一层代理会将连接它的客户端IP追加在X-Forwarded-For右边。
	 */
	public final static String X_FORWARDED_FOR = "X-Forwarded-For";
	
}
