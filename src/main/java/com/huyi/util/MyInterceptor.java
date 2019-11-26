package com.huyi.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: AdminInterceptor 
 * @Description: 个人中心拦截器
 * @author:huyi
 * @date: 2019年11月21日 下午7:36:00
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//从session获取管理员对象
		//如果有就返回session对象，如果没有就返回null
		HttpSession session = request.getSession(false);
		if(null!=session){
			Object object = session.getAttribute("user");
			if(null!=object){
				//放行
				return true;
			}
		}
		
		//没有登录，则转发到登录页面
		request.setAttribute("error", "请登录后在试！");
		request.getRequestDispatcher("/WEB-INF/view/login/login.jsp").forward(request, response);;
		
		return false;
	}
}
