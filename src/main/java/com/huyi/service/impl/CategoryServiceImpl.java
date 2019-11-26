package com.huyi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huyi.bean.Article;
import com.huyi.bean.Category;
import com.huyi.mapper.CategoryMapper;
import com.huyi.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper categoryMapper;
	
	//查询某个栏目下的分类
	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return categoryMapper.selectsByChannelId(channelId);
	}

	//追加栏目发生改变去动态查找对应栏目下的分类
	@Override
	public List<Category> selects(Article article) {
		// TODO Auto-generated method stub
		return categoryMapper.selects(article);
	}

}
