package com.huyi.mapper;

import java.util.List;

import com.huyi.bean.Links;

public interface LinksMapper {

	/**
	 * 
	 * @Title: insert 
	 * @Description:增加友情链接
	 * @param likns
	 * @return
	 * @return: int
	 */
	int insert(Links links);
	/**
	 * 
	 * @Title: selects 查询友情链接
	 * @return
	 * @return: List<Links>
	 */
	List<Links> selects();
}
