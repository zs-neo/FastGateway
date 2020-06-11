/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 15:36
 */
public class HttpUtils {
	
	private final static OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
			.connectTimeout(50L, TimeUnit.SECONDS)
			.readTimeout(60L, TimeUnit.SECONDS)
			.build();
	
	public static Response callHttp(Request request) throws IOException {
		Call call = OK_HTTP_CLIENT.newCall(request);
		return call.execute();
	}
	
	public void query(){
//		RequestBody body = new FormBody.Builder()
//				.add("traceIdList", traceIdList).add("batchPos", batchPos + "").build();
//		String url = String.format("http://localhost:%s/getWrongTrace", port);
//		Request request = new Request.Builder().url(url).post(body).build();
//		Response response = HttpUtils.callHttp(request);
	}
	
}
