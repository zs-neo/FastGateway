/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.bean.entity;

import lombok.Data;

import java.util.Date;

/**
 * 内部服务
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 22:00
 */
@Data
public class SysDO {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 系统名称
	 */
	private String name;
	/**
	 * 系统描述
	 */
	private String desc;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	
}
