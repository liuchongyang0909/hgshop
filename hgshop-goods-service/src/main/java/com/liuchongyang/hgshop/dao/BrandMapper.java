package com.liuchongyang.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.liuchongyang.hgshop.pojo.Brand;
import com.liuchongyang.hgshop.pojo.Spec;

public interface BrandMapper {

	List<Brand> list(String name);

	int deleteBrand(Integer id);

	void delBatchs(int[] ids);

	int addBrand(Brand brand);

	Brand findById(int id);

	int updateBrand(Brand brand);

	@Select("SELECT id,name,first_char as firstChar "
			+ " FROM hg_brand "
			+ "where deleted_flag=0"
			+ " ORDER BY name ")
	List<Brand> listAll();

}
