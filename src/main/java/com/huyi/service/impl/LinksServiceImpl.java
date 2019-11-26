package com.huyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huyi.bean.Links;
import com.huyi.exception.CMSException;
import com.huyi.mapper.LinksMapper;
import com.huyi.service.LinksService;
import com.utils.StringUtil;
@Service
public class LinksServiceImpl implements LinksService {

	@Autowired
	private LinksMapper linksMapper;

	@Override
	public boolean insert(Links links) {
		//调用工具类是否为有效URL
		if(!StringUtil.isHttpUrl(links.getUrl())){
			throw new CMSException("不是有效的URL");
		}
		linksMapper.insert(links);
			
		return true;
	}

	@Override
	public PageInfo<Links> selects(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Links> list = linksMapper.selects();
		return new PageInfo<Links>(list);
	}
	
	
}
