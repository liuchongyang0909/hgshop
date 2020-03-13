package com.liuchongyang.hgshop.dao;

import java.util.List;

import com.liuchongyang.hgshop.pojo.Category;

public interface CategoryMapper {

	/**
	 * 
	 * @Title: tree 
	 * @Description: Ê÷×´ÏÔÊ¾
	 * @return
	 * @return: List<Category>
	 */
	List<Category> tree();

	/**
	 * 
	 * @Title: add 
	 * @Description: Ìí¼Ó
	 * @param category
	 * @return
	 * @return: int
	 */
	int add(Category category);

	/**
	 * 
	 * @Title: delete
	 * @Description: É¾³ý
	 * @param id
	 * @return
	 * @return: int
	 */
	int delete(Integer id);

	/**
	 * 
	 * @Title: update
	 * @Description: ÐÞ¸Ä
	 * @param category
	 * @return
	 * @return: int
	 */
	int update(Category category);

}
