package com.huyi.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huyi.bean.User;
import com.huyi.bean.UserVO;
import com.huyi.exception.CMSException;
import com.huyi.service.UserService;
/**
 * 
 * @ClassName: LoginController 
 * @Description: 登录
 * @author:huyi
 * @date: 2019年11月24日 下午6:17:29
 */
//将RequestMapping放在类上表示指定
@RequestMapping("login")
@Controller
public class LoginController {

	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 去注册页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("gologin")
	public String gologin(){
		return "/login/login";
	}
	
	@GetMapping("reg")
	public String reg(){
		return "login/reg";
	}
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 注册用户
	 * @param model
	 * @param userVO
	 * @return
	 * @return: String
	 */
	@PostMapping("reg")
	public String reg(Model model,UserVO userVO,RedirectAttributes redirect){
		try {
			userService.insertSelective(userVO);
			//注册成功之后进行回显
			//带参数重定向
			redirect.addFlashAttribute("username", userVO.getUsername());
			return "redirect:login";
		}catch (CMSException cms) {
			cms.printStackTrace();
			//封装service抛出的异常
			model.addAttribute("error", cms.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			//封装没有处理的异常
			model.addAttribute("error", "系统异常，请管理员联系!");
		}

		
		return "/login/reg";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录
	 * @param request
	 * @param m
	 * @param user
	 * @return
	 * @return: String
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request,Model m,User user) {
		HttpSession session = request.getSession();
		try {
			user.setLocked(0);
			List<User> users = userService.selectsloginsize(user);
			if(users!=null &&   users.size()>0) {
				if(users.get(0).getLocked()>0) {
					throw new CMSException("账户已被冻结");
				}else {
					if(users.get(0).getRole().equals(0)) {
						System.out.println("普通用户");
						session.setAttribute("user",users.get(0));
						return "redirect:/index";
					}else {
						System.out.println("管理员");
						session.setAttribute("admin",users.get(0));
						return "/admin/index";
					}
				}
			}
		} catch (CMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m.addAttribute("error", e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m.addAttribute("error", "系统异常，请与管理员联系...");
		}
		
		return "/login/login";
	}
	
	/**
	 * 
	 * @Title: zhuxiao 
	 * @Description: 注销
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("zhuxiao")
	public String zhuxiao(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(null!=session)
			session.invalidate();
		return "redirect:/login/gologin";
	}
}
