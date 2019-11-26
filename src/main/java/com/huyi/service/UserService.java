package com.huyi.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huyi.bean.User;
import com.huyi.bean.UserVO;

public interface UserService {

	/**
	 * 
	 * @Title: selects 
	 * @Description: 列表查询
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(String username,Integer page,Integer pageSize);	

    User selectByPrimaryKey(Integer id);

    /**
     * 
     * @Title: updateByPrimaryKey 
     * @Description: 修改当前用户的使用状态
     * @param record
     * @return
     * @return: int
     */
	void updateByPrimaryKeySelective(User user);

	int insertSelective(UserVO userVO);


	List<User> selectsloginsize(User user);



}
