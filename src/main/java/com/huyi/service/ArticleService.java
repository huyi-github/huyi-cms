package com.huyi.service;


import com.github.pagehelper.PageInfo;
import com.huyi.bean.Article;
import com.huyi.bean.ArticleWithBLOBs;

public interface ArticleService {

	/**
	 * 
	 * @Title: selects 
	 * @Description:文章列表查询
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ArticleWithBLOBs article);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);


	int updateByPrimaryKeySelective(ArticleWithBLOBs article);


}
