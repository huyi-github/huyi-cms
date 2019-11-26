package com.huyi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.huyi.bean.Article;
import com.huyi.bean.ArticleWithBLOBs;
import com.huyi.bean.Category;
import com.huyi.bean.Channel;
import com.huyi.bean.Links;
import com.huyi.service.ArticleService;
import com.huyi.service.CategoryService;
import com.huyi.service.ChannelService;
import com.huyi.service.LinksService;
import com.huyi.util.PageUtil;
import com.utils.DateUtil;

/**
 * 
 * @ClassName: IndexController
 * @Description: 首页
 * @author:huyi
 * @date: 2019年11月19日 下午2:31:36
 */

@Controller
public class IndexController {

	@Resource
	private ChannelService channelService;

	@Resource
	private CategoryService categoryService;

	@Resource
	private ArticleService articleService;
	@Autowired
	private LinksService linksService;

	/**
	 * 
	 * @Title: index
	 * @Description: 首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "index", "", "/" })
	public String index(Model model, Article article,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {

		// 只查询审核过的文章和未删除的文章
		article.setStatus(1);
		article.setDeleted(0);

		// 1.显示左侧栏目
		List<Channel> channels = channelService.selects();
		model.addAttribute("channels", channels);

		// 4.默认查询热点文章
		if (null == article.getChannelId()) {
			// 热点文章
			article.setHot(1);
			PageInfo<Article> info = articleService.selects(article, page,
					pageSize);
			String pages = PageUtil.page(page, info.getPages(), "/?hot=1",
					pageSize);

			model.addAttribute("hotArticles", info.getList());
			model.addAttribute("pages", pages);
		}

		// 2.查询栏目下的分类 根据频道查询当前频道下对应的分类
		// 判断文章里面的栏目id是否有值
		if (null != article.getChannelId()) {
			List<Category> categorys = categoryService
					.selectsByChannelId(article.getChannelId());
			model.addAttribute("categorys", categorys);
			PageInfo<Article> info = articleService.selects(article, page,
					pageSize);

			String pages = PageUtil.page(page, info.getPages(), "/?channelId="
					+ article.getChannelId(), pageSize);

			model.addAttribute("articles", info.getList());
			model.addAttribute("pages", pages);
		}
		if (null != article.getCategoryId()) {
			List<Category> categorys = categoryService
					.selectsByChannelId(article.getChannelId());
			model.addAttribute("categorys", categorys);

			// 3.查询分类下的文章 根据分类id查询分类下对应的文章(如果栏目不为空就查询分类下的文章)
			// 判断文章里面的分类id是否有值
			PageInfo<Article> info = articleService.selects(article, page,
					pageSize);

			String pages = PageUtil.page(page, info.getPages(),
					"/?channelId=" + article.getChannelId() + "&categoryId="
							+ article.getCategoryId(), pageSize);

			model.addAttribute("articles", info.getList());
			model.addAttribute("pages", pages);
		}

		// 5.24小时热文
		Article article2 = new Article();
		article2.setHot(1);
		// 24小时之前的时间
		article2.setCreated(DateUtil.get24BeforeHours());

		PageInfo<Article> info = articleService.selects(article2, 1, 5);
		// 封装查询的结果集
		model.addAttribute("article24", info.getList());

		// 6.最新文章
		Article article3 = new Article();
		article3.setStatus(1);
		PageInfo<Article> info2 = articleService.selects(article3, 1, 5);
		model.addAttribute("articlehot", info2.getList());

		// 显示连接
		PageInfo<Links> info5 = linksService.selects(page, pageSize);
		model.addAttribute("links", info5.getList());
		return "index/index";
	}

	/**
	 * 
	 * @Title: select
	 * @Description: 根据id查询文章
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("select")
	public String select(Model model, Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "index/article";
	}
}
