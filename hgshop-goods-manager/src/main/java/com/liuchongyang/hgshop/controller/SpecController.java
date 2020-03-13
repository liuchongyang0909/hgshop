package com.liuchongyang.hgshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.hgshop.pojo.Spec;
import com.liuchongyang.hgshop.service.SpecService;

/**
 * 
 * @ClassName: SpecController 
 * @Description: 规格的管理
 * @author: 86157
 * @date: 2020年3月4日 上午9:46:26
 */
@Controller
@RequestMapping("spec")
public class SpecController {
	
	@Reference
	SpecService specService;
	/**
	 * 
	 * @Title: list 
	 * @Description: 进入规格的列表
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("list")
	public String list(@RequestParam(defaultValue = "1")int page, HttpServletRequest request, @RequestParam(defaultValue = "")String name) {
		PageInfo<Spec> pageInfo = specService.list(name, page);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("queryName", name);
		
		return "spec/list";
	}
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 添加
	 * @param request
	 * @param spec
	 * @return
	 * @return: Object
	 */
	@RequestMapping("add")
	@ResponseBody
	public Object add(HttpServletRequest request,Spec spec) {
		spec.getOptions().removeIf(x->{return x.getOptionName()==null;});
		//调用服务
		int add = specService.add(spec);
		return add>0?"true":"false";
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改规格
	 * @param request
	 * @param spec
	 * @return
	 * @return: String
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest request,Spec spec) {
		spec.getOptions().removeIf(x->{return x.getOptionName()==null;});
		//调用服务
		int result = specService.update(spec);  
		return result >0 ?"success":"false";
		//return "fail";
	}
	
	/**
	 * 
	 * @Title: getSpec 
	 * @Description: 修改的回显
	 * @param request
	 * @param id
	 * @return
	 * @return: Spec
	 */
	@RequestMapping("getSpec")
	@ResponseBody
	public Spec getSpec(HttpServletRequest request, int id){
		return specService.findById(id);
		
	}
	
	/**
	 * 
	 * @Title: delSpec 
	 * @Description: 删除规格(单删)
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("delSpec")
	@ResponseBody
	public String delSpec(HttpServletRequest request,int id) {
		//调用服务
		int delNum = specService.delete(id);
		return delNum>0?"true":"false";
	}
	
	
	@RequestMapping("delSpecBatch")
	@ResponseBody
	public String delSpecBatch(HttpServletRequest request,@RequestParam(name="ids[]") int[] ids) {
		System.out.println("要删除的额数据");
		for (int i : ids) {
			System.out.println(" i is " + i  );
		}
		//调用服务
		int delNum = specService.deleteBatch(ids);
		return delNum>0?"true":"false";
	}
}
