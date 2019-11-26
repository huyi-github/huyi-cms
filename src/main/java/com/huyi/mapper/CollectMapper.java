package com.huyi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.huyi.bean.Collect;

public interface CollectMapper {

	/**
	 * 
	 * @Title: addCollect 
	 * @Description: 添加收藏
	 * @param collect
	 * @return: void
	 */
	@Insert("INSERT into cms_collect set text=#{text},url=#{url},user_id=#{user_Id},created=now()")
	void addCollect(Collect collect);

	List selects();

	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 删除收藏
	 * @param id
	 * @return: void
	 */
	void deleteCollect(Integer id);

}
