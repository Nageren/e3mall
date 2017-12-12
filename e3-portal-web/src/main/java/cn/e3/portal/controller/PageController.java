package cn.e3.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3.content.service.ContentService;
import cn.e3.utils.AdItem;
import cn.e3.utils.JsonUtils;

@Controller
public class PageController {
	
	//注入广告内容服务
	//引入服务
	@Autowired
	private ContentService contentService;
	
	/**
	 * 需求: 跳转到首页
	 * 业务: 跳转门户系统页面之前,需要做一些初始化的工作
	 * 	1.初始化大广告数据
	 *  2.初始化小广告数据
	 *  3.初始化楼层广告数据
	 *  ....
	 */
	
	//注入大广告位的唯一标识
	@Value("${BIG_AD_CATEGORY_ID}")
	private Long BIG_AD_CATEGORY_ID;
	
	
	
	@RequestMapping("index")
	public String showIndex(Model model){
		//初始化大广告数据
		//调用广告内容服务,查询大广告数据
		//大广告位的区域的id是89	,查询时候是根据分类id查询
		List<AdItem>  adlist = contentService.findContentAdList(BIG_AD_CATEGORY_ID);
		//把广告数据转成json字符串
		String adJson = JsonUtils.objectToJson(adlist);
		//页面回显
		model.addAttribute("ad1", adJson);
		return "index";
	}
}
