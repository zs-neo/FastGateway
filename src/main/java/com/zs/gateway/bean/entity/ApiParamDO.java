/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.bean.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * api接口的参数
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 21:50
 */
@Data
public class ApiParamDO {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * api的主键id
	 */
	private Long apiId;
	/**
	 * 参数名称
	 */
	private String name;
	/**
	 * 参数类型
	 */
	private String type;
	/**
	 * 参数顺序
	 */
	private Integer sequence;
	/**
	 * api的版本
	 */
	private Integer version;
	/**
	 * 创建时间
	 */
	private LocalDateTime createdTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime modifiedTime;
	
}
