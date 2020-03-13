package com.liuchongyang.hgshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @ClassName: Properties 
 * @Description: 配置类读取属性文件
 * @author: 86157
 * @date: 2020年3月4日 上午8:46:47
 */
@Configuration
@PropertySource("classpath:hgshop-admin.properties")
public class AdminProperties {
	
	@Value("${admin.name}")
	String adminName;
	
	@Value("${admin.pwd}")
	String password;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
