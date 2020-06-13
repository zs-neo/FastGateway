/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.example.demo.service;

import com.example.demo.bean.Result;
import com.example.demo.bean.User;

import java.util.List;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/13 18:13
 */
public interface UserService {
	
	Result<User> queryUserById(int id);
	
}
