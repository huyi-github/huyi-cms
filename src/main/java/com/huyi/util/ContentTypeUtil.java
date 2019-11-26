
package com.huyi.util;

public enum ContentTypeUtil {
	
	HTML(0,"html"),IMAGE(1,"image");
	
	private Integer code;
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
	private ContentTypeUtil(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
}
