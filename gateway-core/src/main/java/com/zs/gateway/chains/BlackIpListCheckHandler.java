/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway.chains;

import com.zs.gateway.bean.entity.BWIpListDO;
import com.zs.gateway.bean.vo.RequestVO;
import com.zs.gateway.dao.IpDAO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/11 16:46
 */
@Log4j2
public class BlackIpListCheckHandler extends Handler {
	
	@Autowired
	private IpDAO ipDao;
	
	@Override
	public boolean execute(RequestVO requestVO) {
		log.info("black list checking...");
		String clientIp = requestVO.getClientIP();
		List<BWIpListDO> ipDO = ipDao.queryByIp(clientIp);
		if (ipDO == null || ipDO.get(0).getIsBlack().equals(0)) {
			return false;
		} else {
			log.warn("ip {} in black list , refused", clientIp);
			return true;
		}
	}
}