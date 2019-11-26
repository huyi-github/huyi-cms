package com.huyi.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huyi.bean.Links;

public interface LinksService {

	/**
	 * 
	 * @Title: insert 
	 * @Description:增加友情链接
	 * @param likns
	 * @return
	 * @return: int
	 */
	boolean insert(Links links);
	/**
	 * 
	 * @Title: selects 查询友情链接
	 * @return
	 * @return: List<Links>
	 */
	PageInfo<Links> selects(Integer page,Integer pageSize);
}
