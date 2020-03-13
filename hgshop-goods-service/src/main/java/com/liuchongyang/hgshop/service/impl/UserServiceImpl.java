package com.liuchongyang.hgshop.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuchongyang.hgshop.config.AdminProperties;
import com.liuchongyang.hgshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AdminProperties adminPro;
	
	public boolean login(String userName, String passWord) {
		return (adminPro.getAdminName().equals(userName) && adminPro.getPassword().equals(passWord));
	}
	
}
