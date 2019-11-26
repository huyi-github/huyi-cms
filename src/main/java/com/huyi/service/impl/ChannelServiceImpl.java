package com.huyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyi.bean.Channel;
import com.huyi.mapper.ChannelMapper;
import com.huyi.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelMapper channelMapper;

	//查询左侧栏目列表
	@Override
	public List<Channel> selects() {
		return channelMapper.selects();
	}
	

}
