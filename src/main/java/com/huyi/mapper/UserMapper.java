package com.huyi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huyi.bean.User;

public interface UserMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	List<User> selects(@Param("username")String username);
	
	User selectByUsername(String  username);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    /**
     * 
     * @Title: updateByPrimaryKey 
     * @Description: 修改当前用户的使用状态
     * @param record
     * @return
     * @return: int
     */
    int updateByPrimaryKeySelective(User user);

   
    int updateByPrimaryKey(User record);
    
    /**
     * 
     * @Title: selectsloginsize 
     * @Description: 用户登录
     * @param user
     * @return
     * @return: List<User>
     */
	List<User> selectsloginsize(User user);
}