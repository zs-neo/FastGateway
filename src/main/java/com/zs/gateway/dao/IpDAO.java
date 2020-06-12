/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.dao;

import com.zs.gateway.bean.entity.BWIpListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 17:17
 */
@Mapper
public interface IpDAO {
	
	@Select("select * from gw_ip_bwlist where ip=#{ip}")
	List<BWIpListDO> queryByIp(String ip);
	
}
