package com.huyi.mapper;

import java.util.List;

import com.huyi.bean.Channel;

public interface ChannelMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 左侧栏目查询
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}