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
 * @Description: ���Ĺ���
 * @author: 86157
 * @date: 2020��3��4�� ����10:14:06
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
		// �������
		specMapper.addSpec(spec);
		// ������ܻ�ȡ������Id
		// ����ӱ�
		List<SpecOption> options = spec.getOptions();
		//
		int n = 1;
		for (SpecOption specOption : options) {
			// ���������ID
			specOption.setSpecId(spec.getId());
			specMapper.addOptions(specOption);
			n++;
		}
		// ���ص���Ӱ�����ݵ�����
		return n;
	}

	public int update(Spec spec) {
		// TODO Auto-generated method stub
		// ȥ�ӱ���ɾ��
		specMapper.deleteSpecOtions(spec.getId());
		// �޸�����
		specMapper.updateSpec(spec);
		// �����ӱ�
		List<SpecOption> options = spec.getOptions();
		for (SpecOption specOption : options) {
			// ���������id
			specOption.setSpecId(spec.getId());
			specMapper.addOptions(specOption);
		}

		return 1;
	}

	public int delete(int id) {
		/**
		 * ɾ���ӱ�
		 */
		specMapper.deleteSpecOtions(id);
		// ɾ������
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
