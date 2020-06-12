/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.bean;

import com.zs.gateway.enums.CodeMsg;
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
	/**
	 * 数字签名
	 */
	private String sign;
	private T data;
	
	public Result() {
	}
	
	public Result(T data) {
		this.code = 0;
		this.msg = "success";
		this.data = data;
	}
	
	public Result(T data, String sign) {
		this.code = 0;
		this.msg = "success";
		this.sign = sign;
		this.data = data;
	}
	
	public Result(int code, String msg, String sign, T data) {
		this.code = code;
		this.msg = msg;
		this.sign = sign;
		this.data = data;
	}
	
	public Result(CodeMsg codeMsg) {
		this.code = codeMsg.getCode();
		this.msg = codeMsg.getMsg();
		this.sign = null;
		this.data = null;
	}
	
	public static <T> Result<T> success(T data, String sign) {
		return new Result<>(data, sign);
	}
	
	public static <T> Result<T> error(CodeMsg codeMsg) {
		return new Result<>(codeMsg);
	}
	
}
