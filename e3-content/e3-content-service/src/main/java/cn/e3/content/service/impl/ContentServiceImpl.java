package cn.e3.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.content.service.ContentService;
import cn.e3.mapper.TbContentMapper;
import cn.e3.pojo.TbContent;
import cn.e3.pojo.TbContentExample;
import cn.e3.pojo.TbContentExample.Criteria;
import cn.e3.utils.AdItem;
import cn.e3.utils.DataGridPagebean;
import cn.e3.utils.E3mallResult;
@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper  tbContentMapper;
	
	/**
	 * 需求:根据外键id来查询内容
	 * 参数:Long categoryId ,Integer page ,Integer rows
	 * 返回值:分页展示  DataGridPagebean
	 * 发布服务
	 */
	@Override
	public DataGridPagebean findContentListByPage(Long categoryId, Integer page, Integer rows) {
		//创建example对象
		TbContentExample example = new TbContentExample();
		//创建Criteria对象
		Criteria createCriteria = example.createCriteria();
		//设置查询参数 根据外键查询广告数据内容
		createCriteria.andCategoryIdEqualTo(categoryId);
		//设置分页
		PageHelper.startPage(page, rows);
		//执行查询
		List<TbContent> list = tbContentMapper.selectByExample(example);
		//创建pageInfo分页工具类对象,从pageInfo中获取分页数据
		PageInfo<TbContent> pageInfo = new  PageInfo<>(list);
		//创建分页的包装类对象
		DataGridPagebean dataGridPagebean = new DataGridPagebean();
		//设置总记录
		dataGridPagebean.setRows(list);
		//设置总记录数
		dataGridPagebean.setTotal(pageInfo.getTotal());
		
		return dataGridPagebean;
	}

	
	/**
	 * 请求:/content/save
	 * 需求:保存内容广告数据
	 * 参数:TbContent  content
	 * 返回值:E3mallResult 
	 */
	@Override
	public E3mallResult saveContent(TbContent tbContent) {
		Date date = new Date();
		tbContent.setUpdated(date);
		tbContent.setCreated(date);
		tbContentMapper.insertSelective(tbContent);
		
		return E3mallResult.ok();
	}

	//注入常量
	/**
	 *  WIDTH=670
	    WIDTHB=550
		HEIGHT=240
		HEIGHTB=240
	 */
	@Value("${WIDTH}")
	private Integer WIDTH;
	
	@Value("${WIDTHB}")
	private Integer WIDTHB;
	
	@Value("${HEIGHT}")
	private Integer HEIGHT;
	
	@Value("${HEIGHTB}")
	private Integer HEIGHTB;
	
	
	
	
	/**
	 * 需求:查询首页加载的广告数据
	 * 参数:大广广告唯一标识id
	 * 返回值:List<AdItem>
	 */
	@Override
	public List<AdItem> findContentAdList(Long categoryId) {
		//创建广告封装数据的集合对象
		List<AdItem>  adList = new  ArrayList<AdItem>();
		//创建example对象
		TbContentExample example = new TbContentExample();
		//创建Criteria对象
		Criteria createCriteria = example.createCriteria();
		//设置条件
		createCriteria.andCategoryIdEqualTo(categoryId);
		//直接执行查询
		List<TbContent> list = tbContentMapper.selectByExample(example);
		//循环广告数据内容,把广告封装到广告集合对象
		for (TbContent tbContent : list) {
			//创建广告对象AdItem
			AdItem ad = new AdItem();
			//提示信息
			ad.setAlt(tbContent.getSubTitle());
			//售卖地址
			ad.setHref(tbContent.getUrl());
			//图片地址
			ad.setSrc(tbContent.getPic());
			//图片地址2
			ad.setSrc(tbContent.getPic2());
			//图片的宽高
			ad.setWidth(WIDTH);
			ad.setWidthB(WIDTHB);
			ad.setHeight(HEIGHT);
			ad.setHeightB(HEIGHTB);
			
			//将广告对象添加到结合里面
			adList.add(ad);
		}
		
		return adList;
	}


}
