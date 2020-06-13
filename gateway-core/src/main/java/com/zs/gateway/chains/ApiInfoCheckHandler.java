/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.chains;

import com.zs.gateway.bean.entity.ApiDO;
import com.zs.gateway.bean.entity.ApiParamDO;
import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.dao.ApiDAO;
import com.zs.gateway.manager.ApiManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 17:03
 */
@Log4j2
public class ApiInfoCheckHandler extends Handler {
	
	@Resource
	private ApiManager apiManager;
	
	@Override
	public boolean execute(RequestVO requestVO) {
		String code = requestVO.getDataParams().get("code");
		if (code == null) {
			// 没有code说明是HTTP请求,放行到invoke去
			return false;
		}
		// 对于rpc的请求,此对象可以缓存
		ApiDO apiDO = apiManager.queryByCode(code);
		List<ApiParamDO> apiParams = apiManager.queryParamsById(apiDO.getId());
		String[] paramTypes = new String[apiParams.size()];
		if (apiParams == null) {
			log.warn("api info error");
			return true;
		}
		// 按顺序找到对应的参数类型
		for (int i = 0; i < apiParams.size(); i++) {
			paramTypes[apiParams.get(i).getSequence()] = apiParams.get(i).getType();
		}
		String[] params = new String[apiParams.size()];
		// 按顺序放好对应的参数值
		Map<String, String> map = requestVO.getDataParams();
		for (int i = 0; i < apiParams.size(); i++) {
			params[apiParams.get(i).getSequence()] = map.get(apiParams.get(i).getName());
		}
		// 保存
		requestVO.setParamTypes(paramTypes);
		requestVO.setParams(params);
		requestVO.setMethod(apiDO.getMethod());
		requestVO.setInterfaceClass(apiDO.getName());
		log.info("api info ckecking");
		return false;
	}
}
