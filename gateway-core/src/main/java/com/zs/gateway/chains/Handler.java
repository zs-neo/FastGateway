/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.chains;

import com.zs.gateway.bean.vo.RequestVO;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 命令封装了要执行的处理工作单元，其目的是检查或修改由上下文表示的事务的状态。
 * 单个命令可以被组装成一个链，这允许它们完成所需的处理，或者将进一步的处理委托给链中的下一个命令。
 * 命令实现应该以线程安全的方式设计，适合包含在可能由不同线程同时处理的多个链中。
 * 一般来说，这意味着命令类不应该维护实例变量中的状态信息。
 * 相反，应该通过对传递给execute()命令的上下文属性的适当修改来维护状态信息。
 * 命令实现通常在上下文实例中检索和存储状态信息，这些信息作为参数传递给execute()方法.
 *
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 16:02
 */
public abstract class Handler implements Command {
	
	@Override
	public boolean execute(Context context) throws Exception {
		RequestVO requestVO = (RequestVO) context;
		return execute(requestVO);
	}
	
	public abstract boolean execute(RequestVO requestVO);
}
