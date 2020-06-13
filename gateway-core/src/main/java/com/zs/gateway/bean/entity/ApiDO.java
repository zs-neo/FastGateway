/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.bean.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 比如这样的HTTP接口
 * GetMapping("/login")
 * public String login(@RequestParam String username, @RequestParam String password) {...}
 * 还有这样的RPC接口
 * public String login(String username, String password) {...}
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 21:48
 */
@Data
public class ApiDO {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * api唯一编码
	 */
	private String code;
	/**
	 * api方法名称
	 */
	private String name;
	/**
	 * api属于的系统名称
	 */
	private int sysid;
	/**
	 * api的方法名
	 */
	private String method;
	/**
	 * api的版本
	 */
	private String version;
	/**
	 * 创建时间
	 */
	private LocalDateTime createdTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime modifiedTime;
}
