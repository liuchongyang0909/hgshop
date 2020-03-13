package com.liuchongyang.hgshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.hgshop.pojo.Brand;
import com.liuchongyang.hgshop.pojo.Spec;
import com.liuchongyang.hgshop.service.GoodsService;

/**
 * 
 * @ClassName: BrandController 
 * @Description: 品牌管理
 * @author: 86157
 * @date: 2020年3月4日 上午9:49:16
 */
@Controller
@RequestMapping("brand")
public class BrandController {
	@Reference
	GoodsService goodsService;
	
	@RequestMapping("list")
	public String list(@RequestParam(defaultValue = "1")int page, HttpServletRequest request, @RequestParam(defaultValue = "")String name) {
		PageInfo<Brand> pageInfo = goodsService.listBrand(name, page);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("queryName", name);
		
		return "brand/list";
	}
	
	@RequestMapping("delBrand")
	@ResponseBody
	public String delBrand(HttpServletRequest request,int id) {
		//调用服务
		int delNum = goodsService.deleteBrand(id);
		return delNum>0?"true":"false";
	}
	
	@RequestMapping("delBatchs")
	@ResponseBody
	public String delSpecBatch(HttpServletRequest request,@RequestParam(name="ids[]") int[] ids) {
		int delNum = goodsService.delBatchs(ids);
		return delNum>0?"true":"false";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Object add(HttpServletRequest request,Brand brand) {
		//调用服务
		int add = goodsService.addBrand(brand);
		return add>0?"true":"false";
	}
	
	@RequestMapping("getBrand")
	@ResponseBody
	public Brand getBrand(HttpServletRequest request, int id){
		return goodsService.findById(id);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest request,Brand brand) {
		int result = goodsService.updateBrand(brand);  
		return result >0 ?"success":"false";
	}
}
