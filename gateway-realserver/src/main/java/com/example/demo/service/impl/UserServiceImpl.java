/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.example.demo.service.impl;

import com.example.demo.bean.Result;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/13 18:14
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Override
	public Result<User> queryUserById(int id,String code) {
		User user = new User(1, "tom", "123456");
		Result<User> result = new Result<>();
		result.setCode(200);
		result.setMsg("success");
		result.setData(user);
		return result;
	}
}
