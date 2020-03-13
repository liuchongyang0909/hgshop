package com.liuchongyang.hgshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liuchongyang.hgshop.comm.HgShopConstant;
import com.liuchongyang.hgshop.pojo.User;
import com.liuchongyang.hgshop.service.WebUserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Reference
	WebUserService userService;
	
	@RequestMapping("toLogin")
	public String toLogin() {
		return "user/login";
	}
	
	@RequestMapping("login")
	public String login(HttpServletRequest request,
			String username,String password) {
		// ��¼
		User user = userService.login(username, password);
		if(user == null) {
			request.setAttribute("error", "�û����������");
			return "user/login";
		}
		// д��session
		request.getSession().setAttribute(HgShopConstant.USEKEY, user);
		return "redirect:/user/home";
	}
	
	@RequestMapping("toRegister")
	public String toRegister() {
		
		return "user/register";
	}
	
	@RequestMapping("register")
	public String login(HttpServletRequest request,User user) {
		// ע��
		User registerUser = userService.register(user);
		if(registerUser == null) {
			request.setAttribute("error", "ע��ʧ��");
			return "user/register";
		}
		//ע��ɹ� ��ת����½ҳ��
		return "redirect:toLogin";
	}
	
	public String home(HttpServletRequest request) {
		return "user/home";
	}
}
