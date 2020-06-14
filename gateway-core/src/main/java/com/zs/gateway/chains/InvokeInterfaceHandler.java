/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.chains;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.support.DubboGenericInvoke;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.xml.ws.Action;
import java.security.SecureRandom;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 17:06
 */
@Log4j2
public class InvokeInterfaceHandler extends Handler {
	
	@Resource
	private DubboGenericInvoke dubboGenericInvoke;
	
	@Override
	public boolean execute(RequestVO requestVO) {
		log.info("step 6 : interface invoking...");
		String[] paramTypes = requestVO.getParamTypes();
		Object[] params = requestVO.getParams();
		String interfaceClass = requestVO.getApiDO().getName();
		String method = requestVO.getApiDO().getMethod();
		try {
			Object object = dubboGenericInvoke.invokeDubbo(interfaceClass, method, paramTypes, params);
			String jsonData = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
			log.info("get json {}",jsonData);
			requestVO.setData(jsonData);
		} catch (Exception e) {
			log.warn("error in invoke");
			e.printStackTrace();
			return true;
		}
		return false;
	}
}
