package com.liuchongyang.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.hgshop.pojo.Brand;
import com.liuchongyang.hgshop.pojo.Category;
import com.liuchongyang.hgshop.pojo.Sku;
import com.liuchongyang.hgshop.pojo.Spec;
import com.liuchongyang.hgshop.pojo.Spu;
import com.liuchongyang.hgshop.pojo.SpuVo;

/**
 * 
 * @ClassName: GoodsService
 * @Description: TODO
 * @author: 86157
 * @date: 2020��3��3�� ����9:26:49 ******Dubbo �ķ���ӿں�������Ҫ�з�void�ķ���ֵ
 */
public interface GoodsService {
	int addBrand(Brand brand);

	int updateBrand(Brand brand);

	int deleteBrand(Integer id);

	/**
	 * 
	 * @Title: list
	 * @Description: TODO
	 * @param firstChar ����ĸ
	 * @param page      ҳ��
	 * @return
	 * @return: PageInfo<Brand>
	 */
	PageInfo<Brand> listBrand(String firstChar, int page);

	List<Brand> getAllBrands();

	int addCategory(Category category);

	int updateCategory(Category category);

	int deleteCategory(Integer id);

	/**
	 * 
	 * @Title: listCategory
	 * @Description: TODO
	 * @param firstChar ����ĸ
	 * @param page      ҳ��
	 * @return
	 * @return: PageInfo<Brand>
	 */
	PageInfo<Brand> listCategory(String firstChar, int page);

	List<Category> treeCategory();

	int delBatchs(int[] ids);

	Brand findById(int id);

	// spu�Ĺ���
	PageInfo<Spu> listSpu(int page, SpuVo vo);

	int addSpu(Spu spu);

	int updateSpu(Spu spu);

	int deleteSpu(int id);

	Spu getSpu(int id);

	int deleteSpuBatch(int[] id);
	
	// sku�Ĺ���
	PageInfo<Sku>  listSku(int page,Sku sku);
	int addSku(Sku sku);
	Sku getSku(int id);//��ȡ����
	int updateSku(Sku sku);
	int deleteSku(int id);
	int deleteSkuBatch(int[] id);
	
	//����spu ��ȡ���е�sku
	List<Sku>  listSkuBySpu(int spuId);
}
