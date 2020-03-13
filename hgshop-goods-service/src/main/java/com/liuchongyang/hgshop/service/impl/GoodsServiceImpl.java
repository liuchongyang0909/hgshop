package com.liuchongyang.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuchongyang.hgshop.dao.BrandMapper;
import com.liuchongyang.hgshop.dao.CategoryMapper;
import com.liuchongyang.hgshop.dao.SkuMapper;
import com.liuchongyang.hgshop.dao.SpuMapper;
import com.liuchongyang.hgshop.pojo.Brand;
import com.liuchongyang.hgshop.pojo.Category;
import com.liuchongyang.hgshop.pojo.Sku;
import com.liuchongyang.hgshop.pojo.Spec;
import com.liuchongyang.hgshop.pojo.SpecOption;
import com.liuchongyang.hgshop.pojo.Spu;
import com.liuchongyang.hgshop.pojo.SpuVo;
import com.liuchongyang.hgshop.service.GoodsService;

@Service(interfaceClass = GoodsService.class)
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	BrandMapper brandMapper;
	
	@Autowired
	SpuMapper spuMapper;
	
	@Autowired
	SkuMapper skuMapper;
	
	//@Autowired
	//	KafkaTemplate<String,String> kafaTemplate;

	public int addBrand(Brand brand) {
		return brandMapper.addBrand(brand);
	}

	public int updateBrand(Brand brand) {
		return brandMapper.updateBrand(brand);
	}

	public int deleteBrand(Integer id) {
		return brandMapper.deleteBrand(id);
	}

	public PageInfo<Brand> listBrand(String name, int page) {
		
		PageHelper.startPage(page, 5);
		List<Brand> list = brandMapper.list(name);
		PageInfo<Brand> info = new PageInfo<Brand>(list);
		return info;
	}

	public int addCategory(Category category) {
		return categoryMapper.add(category);
	}

	public int updateCategory(Category category) {
		return categoryMapper.update(category);
	}

	public int deleteCategory(Integer id) {
		return categoryMapper.delete(id);
	}

	public PageInfo<Brand> listCategory(String firstChar, int page) {
		return null;
	}

	public List<Category> treeCategory() {
		return categoryMapper.tree();
	}

	@Override
	public int delBatchs(int[] ids) {
		brandMapper.delBatchs(ids);
		return 1;
	}

	@Override
	public Brand findById(int id) {
		return brandMapper.findById(id);
	}

	@Override
	public List<Brand> getAllBrands() {
		return brandMapper.listAll();
	}

	@Override
	public PageInfo<Spu> listSpu(int page, SpuVo vo) {
		PageHelper.startPage(page, 10);
		
		List<Spu> list = spuMapper.list(vo);
		
		PageInfo<Spu> info = new PageInfo<Spu>(list);
		
		return info;
	}

	@Override
	public int addSpu(Spu spu) {
		int cnt =  spuMapper.add(spu);
		return cnt;
	}

	@Override
	public int updateSpu(Spu spu) {
		return spuMapper.update(spu);
	}

	@Override
	public int deleteSpu(int id) {
		return spuMapper.delete(id);
	}

	@Override
	public Spu getSpu(int id) {
		return spuMapper.findById(id);
	}

	@Override
	public int deleteSpuBatch(int[] ids) {
		return spuMapper.deleteSpuBatch(ids);
	}

	@Override
	public PageInfo<Sku> listSku(int page, Sku sku) {
		PageHelper.startPage(page, 5);
		
		List<Sku> list = skuMapper.list(sku);
		
		PageInfo<Sku> info = new PageInfo<Sku>(list);
		
		return info;
	}

	@Override
	public int addSku(Sku sku) {
		int cnt = skuMapper.addSku(sku);
		List<SpecOption> specs = sku.getSpecs();
		for (SpecOption specOption : specs) {
			cnt += skuMapper.addSkuSpec(sku.getId(),specOption);
		}
		
		return cnt;
	}

	@Override
	public Sku getSku(int id) {
		return skuMapper.get(id);
	}

	@Override
	public int updateSku(Sku sku) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSku(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSkuBatch(int[] id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sku> listSkuBySpu(int spuId) {
		return skuMapper.listBySpu(spuId);
	}
	
}
