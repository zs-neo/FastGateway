/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.manager;

import com.zs.gateway.bean.entity.ApiDO;
import com.zs.gateway.bean.entity.ApiParamDO;
import com.zs.gateway.dao.ApiDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/12 21:40
 */
@Component
public class ApiManager {
	
	@Resource
	public ApiDAO apiDAO;
	
	/**
	 * 这里有个逻辑就是每个版本(version)的api的code一定不一样
	 * 但不同版本的api的code可能是一样的，但是现在暂认为code唯一。
	 *
	 * @param code
	 * @return
	 */
	public ApiDO queryByCode(String code) {
		return apiDAO.queryByCode(code);
	}
	
	public List<ApiParamDO> queryParamsById(int apiId) {
		return apiDAO.queryParamsById(apiId);
	}
	
	
}