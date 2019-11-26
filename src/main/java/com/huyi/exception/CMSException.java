package com.huyi.exception;

public class CMSException extends RuntimeException{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -3277641501940326194L;

	public CMSException(){
		
	}
	
	public CMSException(String message){
		super(message);
	}

}
