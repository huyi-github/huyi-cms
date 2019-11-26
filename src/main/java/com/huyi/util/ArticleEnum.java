package com.huyi.util;

public enum ArticleEnum {

	HTML(0,"html"),IMAGE(1,"image");
	
	
	ArticleEnum(Integer code,String name) {
		this.code=code;
		this.name=name;
	}
	
	//获取值
	private Integer code;
	//获取名称
	private String name;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//测试
	/*public static void main(String[] args) {
		System.out.println(HTML.getCode());
		System.out.println(HTML.getName());
	}*/
}
