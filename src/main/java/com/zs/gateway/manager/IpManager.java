/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.manager;

import com.zs.gateway.bean.entity.BWIpListDO;
import com.zs.gateway.dao.IpDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 17:36
 */
@Component
public class IpManager {
	
	@Autowired
	private IpDAO ipDao;
	
	List<BWIpListDO> queryByIp(String ip) {
		return ipDao.queryByIp(ip);
	}
	
}
