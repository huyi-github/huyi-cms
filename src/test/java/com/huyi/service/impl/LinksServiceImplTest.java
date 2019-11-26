package com.huyi.service.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.huyi.bean.Links;
import com.huyi.service.LinksService;


public class LinksServiceImplTest extends JunitParent {

	//友情链接
	@Resource
	private LinksService linksService;
	
	@Test
	public void test() {
		Links links = new Links();
		links.setUrl("https://www.sohu.com");
		links.setText("搜狐");
		linksService.insert(links);
	}

}
