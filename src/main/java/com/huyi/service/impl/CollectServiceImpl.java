package com.huyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huyi.bean.Collect;
import com.huyi.mapper.CollectMapper;
import com.huyi.service.CollectService;

@Service
public class CollectServiceImpl implements CollectService {

	@Autowired
	private CollectMapper mapper;
	
	@Override
	public void addCollect(Collect collect) {
		mapper.addCollect(collect);
	}

	//收藏列表
	@Override
	public PageInfo<Collect> selects(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List list = mapper.selects();
		return new PageInfo<Collect>(list);
	}

	// 删除收藏
	@Override
	public void deleteCollect(Integer id) {
		mapper.deleteCollect(id);
	}

}
