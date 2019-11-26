package com.huyi.service;

import com.github.pagehelper.PageInfo;
import com.huyi.bean.Collect;

public interface CollectService {

	/**
	 * 
	 * @Title: addCollect 
	 * @Description: 添加收藏
	 * @param collect
	 * @return: void
	 */
	void addCollect(Collect collect);

	/**
	 * 
	 * @param pageSize 
	 * @param page 
	 * @Title: selects 
	 * @Description: 收藏列表
	 * @return
	 * @return: PageInfo<Collect>
	 */
	PageInfo<Collect> selects(Integer page, Integer pageSize);

	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description:  删除收藏
	 * @param id
	 * @return: void
	 */
	void deleteCollect(Integer id);

	
}
