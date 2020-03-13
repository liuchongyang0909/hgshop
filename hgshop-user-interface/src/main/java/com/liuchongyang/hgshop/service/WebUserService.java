package com.liuchongyang.hgshop.service;

import com.liuchongyang.hgshop.pojo.User;

public interface WebUserService {
	/**
	 * 
	 * @Title: login 
	 * @Description: �û���¼
	 * @param username
	 * @param pwd
	 * @return
	 * @return: User
	 */
	User login(String username,String pwd);
	
	/**
	 * 
	 * @Title: register 
	 * @Description: �û�ע��
	 * @param user
	 * @return
	 * @return: User
	 */
	User register(User user);
}
