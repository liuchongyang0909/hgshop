package com.liuchongyang.hgshop.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liuchongyang.hgshop.pojo.User;

public interface UserMapper {
	@Select("SELECT * FROM  hg_user "
			+ " WHERE username=#{uname} AND password=#{pwd} limit 1")
	User find(@Param("uname") String username, @Param("pwd") String pwd);

	
	int add(User user);

	@Select("SELECT * FROM hg_user WHERE uid=#{value} ")
	User getById(Integer uid);
}
