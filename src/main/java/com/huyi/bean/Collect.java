package com.huyi.bean;

import java.util.Date;

/**
 * 
 * @ClassName: Collect 
 * @Description: 收藏
 * @author:huyi
 * @date: 2019年11月24日 下午1:25:32
 */
public class Collect {

	private Integer id;
	
	private String text;
	
	private String url;
	
	private Integer user_Id;
	
	private User user;
	
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(Integer user_Id) {
		this.user_Id = user_Id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Collect [id=" + id + ", text=" + text + ", url=" + url
				+ ", user_Id=" + user_Id + ", user=" + user + ", created="
				+ created + "]";
	}
	
	
}
