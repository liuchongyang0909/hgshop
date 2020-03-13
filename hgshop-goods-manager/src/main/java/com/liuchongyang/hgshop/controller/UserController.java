package com.liuchongyang.hgshop.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liuchongyang.hgshop.service.UserService;



@Controller
public class UserController {
	
	@Reference
	UserService userService;
	
	/**
	 * 
	 * @Title: toLogin 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@RequestMapping("tologin")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: TODO
	 * @param name
	 * @param password
	 * @return
	 * @return: String
	 */
	@RequestMapping("login")
	public String login(String name, String password) {
		if(userService.login(name, password)) {
			return "index";
		} else {
			return "login";
		}
	}
}
