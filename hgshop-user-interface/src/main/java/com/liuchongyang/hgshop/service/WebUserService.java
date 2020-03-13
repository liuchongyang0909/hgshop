package com.liuchongyang.hgshop.service;

import com.liuchongyang.hgshop.pojo.User;

public interface WebUserService {
	/**
	 * 
	 * @Title: login 
	 * @Description: 用户登录
	 * @param username
	 * @param pwd
	 * @return
	 * @return: User
	 */
	User login(String username,String pwd);
	
	/**
	 * 
	 * @Title: register 
	 * @Description: 用户注册
	 * @param user
	 * @return
	 * @return: User
	 */
	User register(User user);
}
