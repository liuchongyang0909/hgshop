package com.liuchongyang.hgshop.dao;

import java.util.List;

import com.liuchongyang.hgshop.pojo.Category;

public interface CategoryMapper {

	/**
	 * 
	 * @Title: tree 
	 * @Description: ��״��ʾ
	 * @return
	 * @return: List<Category>
	 */
	List<Category> tree();

	/**
	 * 
	 * @Title: add 
	 * @Description: ���
	 * @param category
	 * @return
	 * @return: int
	 */
	int add(Category category);

	/**
	 * 
	 * @Title: delete
	 * @Description: ɾ��
	 * @param id
	 * @return
	 * @return: int
	 */
	int delete(Integer id);

	/**
	 * 
	 * @Title: update
	 * @Description: �޸�
	 * @param category
	 * @return
	 * @return: int
	 */
	int update(Category category);

}
