package com.huyi.mapper;

import java.util.List;

import com.huyi.bean.Article;
import com.huyi.bean.Category;

public interface CategoryMapper {
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 查询某个栏目下的分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 
     * @Title: selects 
     * @Description: 追加栏目发生改变去动态查找对应栏目下的分类
     * @param article
     * @return
     * @return: List<Category>
     */
	List<Category> selects(Article article);
}