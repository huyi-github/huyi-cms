package com.huyi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huyi.bean.User;
import com.huyi.bean.UserVO;
import com.huyi.exception.CMSException;
import com.huyi.mapper.UserMapper;
import com.huyi.service.UserService;
import com.huyi.util.Md5Util;
import com.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	//列表查询+模糊+分页
	@Override
	public PageInfo<User> selects(String username, Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> list = mapper.selects(username);
		return new PageInfo<User>(list);
	}

	//用户添加
	//添加后台验证，保证代码的健壮性，也就是防止用户不按照规则进行注册
	@Override
	public int insertSelective(UserVO userVO) {
		//如果用户为null,说明没有传值
		if(userVO==null){
			//CMSException是自定义异常类
			throw new CMSException("用户名或密码必须输入");
		}
		
		//其他情况判断
		//使用字符串工具类，判断是否有值
		if(!(StringUtil.hasText(userVO.getUsername()))){
			throw new CMSException("用户名必须输入");
		}
		if(!(userVO.getUsername().length()>=2 && userVO.getUsername().length()<=10)){
			throw new CMSException("用户名在[2-10]位");
		}
		if(!(StringUtil.hasText(userVO.getPassword()))){
			throw new CMSException("密码必须输入");
		}
		if(!(userVO.getPassword().length()>=3 && userVO.getPassword().length()<=15)){
			throw new CMSException("密码在[3-15]位");
		}
		if(!(userVO.getPassword().equals(userVO.getRegpassword()))){
			throw new CMSException("两次密码必须输入一致");
		}
		
		//对密码进行加密
		String md5Encoding = Md5Util.md5Encoding(userVO.getPassword());
		userVO.setPassword(md5Encoding);
		
		//注册的时候初始化用户
		userVO.setLocked(0);
		userVO.setRole(0);
		userVO.setCreated(new Date());
		userVO.setUpdated(new Date());
		
		return mapper.insertSelective(userVO);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	//修改当前用户的使用状态
	@Override
	public void updateByPrimaryKeySelective(User user) {
		mapper.updateByPrimaryKeySelective(user);
	}


	@Override
	public List<User> selectsloginsize(User user) {
		if(user==null) {
			throw  new  CMSException("用户名和密码必须输入");
		}
		if(!StringUtil.hasText(user.getUsername())) {
			throw  new  CMSException("用户名必须输入");
		}
		if(!StringUtil.hasText(user.getPassword())) {
			throw  new  CMSException("密码必须输入");
		}
		//将界面输入的密码转成md5
		//将这个数据和数据库中的密码进行匹配
		String md5Encoding = Md5Util.md5Encoding(user.getPassword());
		user.setPassword(md5Encoding);
		//界面传过来的值 检查用户是否存在
		List<User> list = mapper.selectsloginsize(user);
			if(!(md5Encoding.equals(list.get(0).getPassword()))) {
				throw  new  CMSException("密码错误");
			}
			if(!(list.get(0).getUsername().equals(user.getUsername()))) {
				throw  new  CMSException("用户名错误");
			}
		return  list;
	}
	




}
