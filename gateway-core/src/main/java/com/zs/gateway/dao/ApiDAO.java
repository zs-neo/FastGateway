/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.dao;

import com.zs.gateway.bean.entity.ApiDO;
import com.zs.gateway.bean.entity.ApiParamDO;
import com.zs.gateway.bean.entity.BWIpListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/13 9:07
 */
@Mapper
public interface ApiDAO {
	
	@Select("select * from gw_api where code=#{code}")
	ApiDO queryByCode(String code);
	
	@Select("select * from gw_api_param where api_id=#{id}")
	List<ApiParamDO> queryParamsById(int id);
	
}
