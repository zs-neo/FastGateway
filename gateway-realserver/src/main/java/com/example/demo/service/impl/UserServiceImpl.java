/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.example.demo.service.impl;

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
	public User queryUserById(int id) {
		return new User(1, "tom", "123456");
	}
}
