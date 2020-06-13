/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.bean.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ip管理
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 17:25
 */
@Data
public class BWIpListDO {
	
	private Integer id;
	private String ip;
	private Integer isBlack;
	private Integer isWhite;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
}
