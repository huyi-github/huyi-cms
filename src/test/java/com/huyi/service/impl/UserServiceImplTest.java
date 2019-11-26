package com.huyi.service.impl;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huyi.bean.User;
import com.huyi.service.UserService;

//把启动spring容器的注解提取出来然后测试类继承JunitParent来启动spring
public class UserServiceImplTest extends JunitParent{

	@Autowired
	private UserService userService;
	
	@Test
	public void testSelects() {
	}

	//验证用户添加
	@Test
	public void testInsertSelective() {
		User user = new User();
		user.setUsername("冯屎蛋");
		user.setPassword("456");
		//userService.insertSelective(user);
	}

	@Test
	public void testSelectByPrimaryKey() {
	}

	@Test
	public void testUpdateByPrimaryKey() {
	}

}
