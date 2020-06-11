/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.bean.vo;

import lombok.Data;
import org.apache.commons.chain.impl.ContextBase;

import java.util.Map;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 15:11
 */
public class RequestVO extends ContextBase {
	
	String version;
	String systemName;
	String interfaceName;
	String methodName;
	String signature;
	String param;
	
	Map<String,String> params;
	
	
}
