package com.liuchongyang.hgshop.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuchongyang.hgshop.dao.UserMapper;
import com.liuchongyang.hgshop.pojo.User;
import com.liuchongyang.hgshop.service.WebUserService;

@Service(interfaceClass = WebUserService.class)
public class WebUserServcImpl implements WebUserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User login(String username, String pwd) {
		return userMapper.find(username,DigestUtils.md5Hex(pwd));
	}

	@Override
	public User register(User user) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		
		if(userMapper.add(user)>0) {
			// 根据自增id 再反查一次用户
			return userMapper.getById(user.getUid());
		}
		
		return null;
	}

}
