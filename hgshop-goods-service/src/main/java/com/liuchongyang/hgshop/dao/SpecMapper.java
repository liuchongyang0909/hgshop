package com.liuchongyang.hgshop.dao;

import java.util.List;

import com.liuchongyang.hgshop.pojo.Spec;
import com.liuchongyang.hgshop.pojo.SpecOption;

public interface SpecMapper {

	List<Spec> list(String name);

	int addSpec(Spec spec);

	int addOptions(SpecOption specOption);

	int updateSpec(Spec spec);

	int deleteSpecOtions(int id);

	int deleteSpec(int id);

	Spec get(int id);

	int deleteSpecOtionsBath(int[] ids);
	
	int deleteSpecBatch(int[] ids);

	List<Spec> listAll();
}
