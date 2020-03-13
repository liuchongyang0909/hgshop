package com.liuchongyang.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuchongyang.hgshop.dao.SpecMapper;
import com.liuchongyang.hgshop.pojo.Spec;
import com.liuchongyang.hgshop.pojo.SpecOption;
import com.liuchongyang.hgshop.service.SpecService;

/**
 * 
 * @ClassName: SpecServiceImpl
 * @Description: 规格的管理
 * @author: 86157
 * @date: 2020年3月4日 上午10:14:06
 */
@Service(interfaceClass = SpecService.class)
public class SpecServiceImpl implements SpecService {

	@Autowired
	SpecMapper specMapper;

	public PageInfo<Spec> list(String name, int page) {
		PageHelper.startPage(page, 5);
		List<Spec> list = specMapper.list(name);
		PageInfo<Spec> info = new PageInfo<Spec>(list);
		return info;
	}

	public int add(Spec spec) {
		// 添加主表
		specMapper.addSpec(spec);
		// 这里才能获取到主建Id
		// 添加子表
		List<SpecOption> options = spec.getOptions();
		//
		int n = 1;
		for (SpecOption specOption : options) {
			// 设置主表的ID
			specOption.setSpecId(spec.getId());
			specMapper.addOptions(specOption);
			n++;
		}
		// 返回的是影响数据的条数
		return n;
	}

	public int update(Spec spec) {
		// TODO Auto-generated method stub
		// 去子表中删除
		specMapper.deleteSpecOtions(spec.getId());
		// 修改主表
		specMapper.updateSpec(spec);
		// 插入子表
		List<SpecOption> options = spec.getOptions();
		for (SpecOption specOption : options) {
			// 设置主表的id
			specOption.setSpecId(spec.getId());
			specMapper.addOptions(specOption);
		}

		return 1;
	}

	public int delete(int id) {
		/**
		 * 删除子表
		 */
		specMapper.deleteSpecOtions(id);
		// 删除主表
		specMapper.deleteSpec(id);
		return 1;
	}

	public Spec findById(int id) {
		return specMapper.get(id);
	}

	public int deleteBatch(int[] ids) {
		specMapper.deleteSpecOtionsBath(ids);

		specMapper.deleteSpecBatch(ids);
		return 1;
	}

	@Override
	public List<Spec> listAll() {
		return null;
	}

}
