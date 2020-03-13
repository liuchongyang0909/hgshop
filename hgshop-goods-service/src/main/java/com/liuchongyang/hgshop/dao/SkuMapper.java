package com.liuchongyang.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuchongyang.hgshop.pojo.Sku;
import com.liuchongyang.hgshop.pojo.SpecOption;

public interface SkuMapper {

	List<Sku> list(Sku sku);

	Sku get(int id);
	
	int addSku(Sku sku);

	int addSkuSpec(@Param("skuId") int skuId,@Param("so") SpecOption so);

	List<Sku> listBySpu(int spuId);

}
