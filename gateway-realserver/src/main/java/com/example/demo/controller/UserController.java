/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.example.demo.controller;

import com.example.demo.bean.Result;
import com.example.demo.bean.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/14 9:01
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping(value = "/login")
	public Result<User> login(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println("get - " + username + " " + password);
		User user = new User(1, username, password);
		Result<User> result = new Result<>();
		result.setCode(200);
		result.setMsg("success");
		result.setData(user);
		return result;
	}
	
}
