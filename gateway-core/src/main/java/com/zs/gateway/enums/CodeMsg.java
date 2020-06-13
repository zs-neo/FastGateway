/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.enums;

import lombok.*;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 21:21
 */
@ToString
@Getter
public class CodeMsg {
	
	private int code;
	private String msg;
	
	public CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 通用异常5001XX
	 */
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	
	/**
	 * 登录模块 5002XX
	 */
	public static CodeMsg USERNAME_EMPTY = new CodeMsg(500210, "用户名不能为空");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
	
	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}
}
