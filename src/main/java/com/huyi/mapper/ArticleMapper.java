package com.huyi.mapper;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huyi.bean.Article;
import com.huyi.bean.ArticleWithBLOBs;
import com.huyi.bean.User;

public interface ArticleMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询文章列表
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selects(Article article);
	
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs article);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

	int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs article);
}