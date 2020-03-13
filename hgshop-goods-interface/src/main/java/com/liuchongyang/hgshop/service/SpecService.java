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
	 * @Description: ��id����һ���������޸ĵĻ���
	 * @param id
	 * @return
	 * @return: Spec
	 */
	Spec findById(int id);
	/**
	 * 
	 * @Title: deleteBatch 
	 * @Description: ����ɾ��
	 * @param id
	 * @return
	 * @return: int
	 */
	int deleteBatch(int[] id);
}
