package com.huyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huyi.bean.Article;
import com.huyi.bean.ArticleWithBLOBs;
import com.huyi.bean.User;
import com.huyi.mapper.ArticleMapper;
import com.huyi.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public PageInfo<Article> selects(Article article,Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> list = articleMapper.selects(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ArticleWithBLOBs article) {
		// TODO Auto-generated method stub
		return articleMapper.insertSelective(article);
	}

	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleWithBLOBs article) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKeySelective(article);
	}

}
