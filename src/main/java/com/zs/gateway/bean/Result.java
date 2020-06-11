/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 21:19
 */
@Getter
@Setter
@ToString
public class Result<T> {
	
	private int code;
	private String msg;
	private T data;
	
	public Result(T data) {
		this.code = 0;
		this.msg = "success";
		this.data = data;
	}
	
	public Result(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
}
