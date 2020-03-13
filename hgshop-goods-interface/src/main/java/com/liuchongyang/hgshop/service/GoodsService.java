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
 * @date: 2020年3月3日 上午9:26:49 ******Dubbo 的服务接口函数必须要有非void的返回值
 */
public interface GoodsService {
	int addBrand(Brand brand);

	int updateBrand(Brand brand);

	int deleteBrand(Integer id);

	/**
	 * 
	 * @Title: list
	 * @Description: TODO
	 * @param firstChar 首字母
	 * @param page      页码
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
	 * @param firstChar 首字母
	 * @param page      页码
	 * @return
	 * @return: PageInfo<Brand>
	 */
	PageInfo<Brand> listCategory(String firstChar, int page);

	List<Category> treeCategory();

	int delBatchs(int[] ids);

	Brand findById(int id);

	// spu的管理
	PageInfo<Spu> listSpu(int page, SpuVo vo);

	int addSpu(Spu spu);

	int updateSpu(Spu spu);

	int deleteSpu(int id);

	Spu getSpu(int id);

	int deleteSpuBatch(int[] id);
	
	// sku的管理
	PageInfo<Sku>  listSku(int page,Sku sku);
	int addSku(Sku sku);
	Sku getSku(int id);//获取详情
	int updateSku(Sku sku);
	int deleteSku(int id);
	int deleteSkuBatch(int[] id);
	
	//根据spu 获取所有的sku
	List<Sku>  listSkuBySpu(int spuId);
}
