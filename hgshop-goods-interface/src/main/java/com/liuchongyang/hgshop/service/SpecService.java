package com.liuchongyang.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.hgshop.pojo.Spec;

public interface SpecService {
	PageInfo<Spec> list(String name,int page);
	
	List<Spec> listAll();
	
	int add(Spec spec);
	
	int update(Spec spec);
	
	int delete(int id);
	
	/**
	 * 
	 * @Title: findById 
	 * @Description: 用id查找一个，用于修改的回显
	 * @param id
	 * @return
	 * @return: Spec
	 */
	Spec findById(int id);
	/**
	 * 
	 * @Title: deleteBatch 
	 * @Description: 批量删除
	 * @param id
	 * @return
	 * @return: int
	 */
	int deleteBatch(int[] id);
}
