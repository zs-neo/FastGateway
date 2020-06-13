/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.example.demo.bean;

import java.io.Serializable;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/13 21:40
 */
public class Result<T> implements Serializable {
	
	private static final long serialVersionUID = -6249813965752294035L;
	
	private int code;
	private String msg;
	private T data;
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
