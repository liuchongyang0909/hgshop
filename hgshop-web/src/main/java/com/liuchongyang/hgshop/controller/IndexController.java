package com.liuchongyang.hgshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.hgshop.pojo.Sku;
import com.liuchongyang.hgshop.pojo.Spu;
import com.liuchongyang.hgshop.pojo.SpuVo;
import com.liuchongyang.hgshop.service.GoodsService;

@Controller
public class IndexController {
	@Reference
	GoodsService goodsService;
	
	@RequestMapping({"/","index"})
	public String index(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="0") int catId) {
		// 获取商品的数据
		PageInfo<Spu> listSpu = goodsService.listSpu(page, new SpuVo());
		request.setAttribute("pageInfo", listSpu);
		return "index";
	}
	
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,int spuId) {
		// spu
		Spu spu = goodsService.getSpu(spuId);
		//sku 
		List<Sku> skuList = goodsService.listSkuBySpu(spuId);
		
		request.setAttribute("spu", spu);
		request.setAttribute("skus", skuList);
		return "detail";
	}
}
