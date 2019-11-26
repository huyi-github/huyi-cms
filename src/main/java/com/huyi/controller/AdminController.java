package com.huyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.huyi.bean.Article;
import com.huyi.bean.ArticleWithBLOBs;
import com.huyi.bean.Links;
import com.huyi.bean.User;
import com.huyi.service.ArticleService;
import com.huyi.service.LinksService;
import com.huyi.service.UserService;
import com.huyi.util.PageUtil;
/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员后台
 * @author:huyi
 * @date: 2019年11月14日 下午7:10:33
 */

//表示"指定"，也就是指定在admin路径下的jsp页面
@RequestMapping("admin")
@Controller
public class AdminController {

	//用户
	@Autowired
	private UserService userService;
	
	//文章
	@Autowired
	private ArticleService articleService;

	//友情链接
	@Autowired
	private LinksService linksService;
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询友情链接列表
	 * @param model
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@GetMapping("links")
	public String selects(Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize){
		PageInfo<Links> info = linksService.selects(page, pageSize);
		model.addAttribute("links",info.getList());
		String pages = PageUtil.page(page, info.getPages(), "/admin/links", pageSize);
		model.addAttribute("pages", pages);
		return "admin/links";
	}
	/**
	 * 
	 * @Title: insert 
	 * @Description: 去友情链接添加页面
	 * @return
	 * @return: String
	 */
	@GetMapping("insert")
	public String goInsert(){
		return "admin/insertLinks";
	}
	
	/**
	 * 
	 * @Title: insertLinks 
	 * @Description:添加链接
	 * @param links
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("insertlinks")
	public boolean insertLinks(Links links){
		try {
			linksService.insert(links);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入管理员后台
	 * @return
	 * @return: String
	 */
	//也就是访问admin层级下的页面输入index,/,空格都可以进入管理员后台
	@RequestMapping(value = {"index","/",""})
	public String index(){
		
		return "admin/index";
	}
	
	/**
	 * 
	 * @Title: users 
	 * @Description: 用户列表展示
	 * @param model
	 * @param page
	 * @param pageSize
	 * @param username
	 * @return
	 * @return: String
	 */
	//GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。该注解将HTTP Get 映射到 特定的处理方法上
	@GetMapping("users")
	public String users(Model model,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "5")Integer pageSize,@RequestParam(defaultValue = "")String username){
		PageInfo<User> info = userService.selects(username, page, pageSize);
		
		//分页
		String pages = PageUtil.page(page, info.getPages(), "/admin/users?username="+username, pageSize);
		
		model.addAttribute("users", info.getList());
		model.addAttribute("username",username);
		model.addAttribute("pages",pages);
		return "admin/users";
	}
	
	/**
	 * 
	 * @Title: articles 
	 * @Description:文章列表
	 * @param model
	 * @param page
	 * @param pageSize
	 * @param article
	 * @return
	 * @return: String
	 */
	@GetMapping("articles")
	public String articles(Model model,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "5")Integer pageSize,Article article){
		
		System.err.println("admin"+article);
		
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		//分页
		String pages = PageUtil.page(page, info.getPages(), "/admin/articles?title="+article.getTitle()+"&status="+article.getStatus(), pageSize);	
		
		model.addAttribute("articles", info.getList());
		model.addAttribute("article",article);
		model.addAttribute("pages",pages);
		return "admin/articles";
	}
	
	/**
	 * 
	 * @Title: updateLocked 
	 * @Description: 修改当前用户的使用状态
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("updateLocked")
	public boolean updateLocked(User user){
		try {
			userService.updateByPrimaryKeySelective(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: updateHot 
	 * @Description:设置热门文章
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("updateArticle")
	public boolean updateArticle(ArticleWithBLOBs article){
		return articleService.updateByPrimaryKeySelective(article)>0;
	}
	
	/**
	 * 
	 * @Title: detail 
	 * @Description: 单个文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("article")
	public String detail(Model model,Integer id){
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		
		return "admin/article";
	}
}
