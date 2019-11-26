package com.huyi.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.huyi.bean.Article;
import com.huyi.bean.ArticleWithBLOBs;
import com.huyi.bean.Category;
import com.huyi.bean.Channel;
import com.huyi.bean.Collect;
import com.huyi.bean.User;
import com.huyi.service.ArticleService;
import com.huyi.service.CategoryService;
import com.huyi.service.ChannelService;
import com.huyi.service.CollectService;
import com.huyi.util.PageUtil;
/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author:huyi
 * @date: 2019年11月24日 下午6:16:40
 */
@Controller
@RequestMapping("my")
public class MyController {

	@Autowired
	private ChannelService channelService;

	@Autowired
	private CategoryService categoryService;

	@Resource
	private ArticleService articleService;

	@Resource
	private CollectService collectService;

	/**
	 * 
	 * @Title: index
	 * @Description: 进入个人中心首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "", "/", "index" })
	public String index() {
		return "/my/index";
	}

	/**
	 * 
	 * @Title: myArticles
	 * @Description: 显示个人文章
	 * @param model
	 * @param article
	 * @return
	 * @return: String
	 */
	@RequestMapping("myArticles")
	public String myArticles(HttpServletRequest request, Model model,
			Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		System.err.println("进入了============");
		// 从session获取当前登录人的信息
		HttpSession session = request.getSession();
		if (null == session) {
			return "redirect:/my/index";
		}
		User user = (User) session.getAttribute("user");

		// 查询自己的文章
		article.setUserId(user.getId());

		PageInfo<Article> info = articleService
				.selects(article, page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/my/myArticles",
				pageSize);
		model.addAttribute("articles", info.getList());
		model.addAttribute("article", article);
		model.addAttribute("pages", pages);
		return "my/articles";
	}

	/**
	 * 
	 * @Title: publish
	 * @Description: 去发布文章的主页面
	 * @return
	 * @return: String
	 */
	@GetMapping("/publish")
	public String publish() {
		return "my/publish";
	}

	/**
	 * 
	 * @Title: getchannel
	 * @Description: 进入发布文章界面核心函数追加栏目
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@PostMapping("getchannel")
	public List<Channel> getchannel() {
		List<Channel> list = channelService.selects();
		System.out.println("追加" + list);
		return list;
	}

	/**
	 * 
	 * @Title: getcategory
	 * @Description:追加栏目发生改变去动态查找对应栏目下的分类
	 * @param article
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@PostMapping("getcategory")
	public List<Category> getcategory(Article article) {
		List<Category> list = categoryService.selects(article);
		return list;
	}

	/**
	 * 
	 * @Title: insertArticle
	 * @Description: 发布文章
	 * @param request
	 * @param article
	 * @param file
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("insertArticle")
	public boolean insertArticle(HttpServletRequest request,
			ArticleWithBLOBs article, MultipartFile file) {
		// System.err.println(article);
		// 文件上传
		if (!file.isEmpty()) {
			// 文件上传地址
			String path = "d:/pic/";
			// 防止文件重名，需要使用UUID的方式改变文件上传的名称
			// 获取文件的原始名字
			String oldName = file.getOriginalFilename();
			// 切割文件的名称然后在拼上UUID组成新的字符串
			String fileName = UUID.randomUUID()
					+ oldName.substring(oldName.lastIndexOf("."));

			try {
				// 将文件写入硬盘
				file.transferTo(new File(path + fileName));

				// 设置文章的标题图片
				article.setPicture(fileName);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// 从session获取当前登录人的信息
		HttpSession session = request.getSession();
		if (null == session) {
			return false;
		}
		User user = (User) session.getAttribute("user");

		article.setUserId(user.getId());// 文章作者
		article.setStatus(0);// 未审核
		article.setDeleted(0);// 未删除
		article.setHits(0);// 点击量默认0
		article.setHot(0);// 默认非热门
		article.setCreated(new Date());
		article.setUpdated(new Date());
		// 保存文章
		System.err.println("发布的文章" + article);
		return articleService.insertSelective(article) > 0;
	}

	/**
	 * 
	 * @Title: uodate
	 * @Description: 跳转到修改页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("update")
	public String uodate(Model model, Integer id) {
		// 根据id查询到对应的文章
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "my/publishupdate";
	}

	/**
	 * 
	 * @Title: updateArticle
	 * @Description: 修改文章
	 * @param request
	 * @param article
	 * @param file
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("updateArticle")
	public boolean updateArticle(HttpServletRequest request,
			ArticleWithBLOBs article, MultipartFile file) {
		// System.err.println(article);
		// 文件上传
		if (!file.isEmpty()) {
			// 文件上传地址
			String path = "d:/pic/";
			// 防止文件重名，需要使用UUID的方式改变文件上传的名称
			// 获取文件的原始名字
			String oldName = file.getOriginalFilename();
			// 切割文件的名称然后在拼上UUID组成新的字符串
			String fileName = UUID.randomUUID()
					+ oldName.substring(oldName.lastIndexOf("."));

			try {
				// 将文件写入硬盘
				file.transferTo(new File(path + fileName));

				// 设置文章的标题图片
				article.setPicture(fileName);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// 修改时间
		article.setUpdated(new Date());
		// 保存文章
		System.err.println("发布的文章" + article);
		return articleService.updateByPrimaryKeySelective(article) > 0;
	}

	/**
	 * 
	 * @Title: publishPic
	 * @Description: 去发表图片集页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("gopublishPic")
	public String publishPic() {
		return "/my/publishPic";
	}

	/**
	 * 
	 * @Title: addCollect
	 * @Description: 添加收藏
	 * @param request
	 * @param collect
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("addCollect")
	public boolean addCollect(HttpServletRequest request, Collect collect) {

		HttpSession session = request.getSession();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			collect.setUser_Id(user.getId());
			collectService.addCollect(collect);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Title: goCollect 
	 * @Description: 收藏列表
	 * @param model
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("goCollect")
	public String goCollect(Model model,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="3")Integer pageSize) {
		PageInfo<Collect> info = collectService.selects(page,pageSize);
		model.addAttribute("collects", info.getList());
		String pages = PageUtil.page(page, info.getPages(), "/my/goCollect", pageSize);
		model.addAttribute("pages", pages);
		return "/my/collect";
	}

	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 根据id查询文章
	 * @param id
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("select")
	public String select(Model model, Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "index/article";
	}
	
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description:删除收藏
	 * @param id
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("deleteCollect")
	public boolean deleteCollect(Integer id) {

		try {
			collectService.deleteCollect(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}