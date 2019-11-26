package com.huyi.service;

import java.util.List;

import com.huyi.bean.Article;
import com.huyi.bean.Category;

public interface CategoryService {

	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 查询某个栏目下的分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);

	/**
	 * 
	 * @Title: selects 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selects(Article article);
}
