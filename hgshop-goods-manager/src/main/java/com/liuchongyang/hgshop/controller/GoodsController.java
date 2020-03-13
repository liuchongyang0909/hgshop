package com.liuchongyang.hgshop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.liuchongyang.hgshop.pojo.Brand;
import com.liuchongyang.hgshop.pojo.Sku;
import com.liuchongyang.hgshop.pojo.Spec;
import com.liuchongyang.hgshop.pojo.SpecOption;
import com.liuchongyang.hgshop.pojo.Spu;
import com.liuchongyang.hgshop.pojo.SpuVo;
import com.liuchongyang.hgshop.service.GoodsService;
import com.liuchongyang.hgshop.service.SpecService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Reference
	GoodsService goodService;;
	
	@Reference
	SpecService specService;
	
	/**
	 * 
	 * @Title: list 
	 * @Description: 列表
	 * @param request
	 * @param page 页码
	 * @param spuVo 查询条件
	 * @return
	 * @return: String
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request ,
			@RequestParam (defaultValue="1") int page,
			SpuVo spuVo) {
		PageInfo<Spu> listSpu = goodService.listSpu(page, spuVo);
		request.setAttribute("pageInfo", listSpu);
		return "goods/list";
	}
	
	@RequestMapping("toadd")
	public String toadd(HttpServletRequest request ) {
		List<Brand> allBrands = goodService.getAllBrands();
		request.setAttribute("brands", allBrands);
		return "goods/add";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request,Spu spu,@RequestParam(value="file") MultipartFile file ) throws IllegalStateException, IOException {
		

		String filePath=processFile(file);
		
		spu.setSmallPic(filePath);
		
		int result = goodService.addSpu(spu);
		
		return result>0?"success":"failed";
		
	}
	
	/**
	 * 
	 * @Title: downLoad 
	 * @Description: TODO
	 * @param response
	 * @param filename
	 * @throws FileNotFoundException
	 * @return: void
	 */
	@RequestMapping("down")
	public void downLoad(HttpServletResponse response, String filename) throws FileNotFoundException {
	    // 读到流中
	    InputStream inStream = new FileInputStream("d:\\pic\\"+filename);// 文件的存放路径
	    // 设置输出的格式
	    response.reset();
	    response.setContentType("bin");
	    response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
	   
	    // 循环取出流中的数据
	    byte[] b = new byte[1024];
	    int len;
	    try {
	      while ((len = inStream.read(b)) > 0)
	        response.getOutputStream().write(b, 0, len);
	      inStream.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	
	}
	
	/**
	 * 
	 * @Title: processFile 
	 * @Description: 上传文件
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @return: String
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			return "";
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		return destFileName.substring(7);
	}
	
	@RequestMapping("skulist")
	public String skulist(HttpServletRequest request ,
			@RequestParam (defaultValue="1") int page,Sku sku) {
		PageInfo<Sku> listSku = goodService.listSku(page, sku);
		request.setAttribute("pageInfo", listSku);
		return "sku/list";
	}
	
	@RequestMapping("skuDetail")
	public String skulist(HttpServletRequest request ,int id) {
		Sku sku = goodService.getSku(id);
		request.setAttribute("sku", sku);
		return "sku/detail";
	}
	
	/**
	 * 
	 * @Title: toaddSku 
	 * @Description: 跳转到sku添加页面
	 * @param request
	 * @param spuId
	 * @return
	 * @return: String
	 */
	@RequestMapping("toaddSku")
	public String toaddSku(HttpServletRequest request ,int spuId) {
		// 获取要添加的商品
		Spu spu = goodService.getSpu(spuId);
		request.setAttribute("spu", spu);
		
		// 属性名称
		List<Spec> list = specService.listAll();
		System.out.println("list is " + list);
		request.setAttribute("specs", list);
		
		return "sku/add";
	}
	
	@RequestMapping("addSku")
	@ResponseBody
	public String addSku(HttpServletRequest request ,Sku sku,int[] specIds,@RequestParam(value="specOptionIds") int[] specOptionIds) {
		List<SpecOption> specs = new ArrayList<>();
		
		System.out.println("specIds + " + specIds.length + " and specOptionIds is " + specOptionIds.length);
		
		
		for (int i = 0; i < specIds.length && i < specOptionIds.length; i++) {
			int j = specIds[i];
			SpecOption specOption = new SpecOption();
			//属性的id
			specOption.setSpecId(specIds[i]);
			// 具体的属性值 的id
			specOption.setId(specOptionIds[i]);
			specs.add(specOption);
		}
		//存放属性的数据
		sku.setSpecs(specs);
		int addSku = goodService.addSku(sku);
		
		return addSku>0?"success":"failed";
	}
}
