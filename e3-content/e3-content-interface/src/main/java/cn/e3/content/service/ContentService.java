package cn.e3.content.service;

import java.util.List;

import cn.e3.pojo.TbContent;
import cn.e3.utils.AdItem;
import cn.e3.utils.DataGridPagebean;
import cn.e3.utils.E3mallResult;

public interface ContentService {
	/**
	 * 需求:根据外键id来查询内容
	 * 参数:Long categoryId ,Integer page ,Integer rows
	 * 返回值:分页展示  DataGridPagebean
	 */
	public  DataGridPagebean findContentListByPage(Long categoryId,Integer page ,Integer rows);

	/**
	 * 请求:/content/save
	 * 需求:保存内容广告数据
	 * 参数:TbContent  content
	 * 返回值:E3mallResult 
	 */
	public E3mallResult saveContent(TbContent  tbContent);
	
	/**
	 * 需求:查询首页加载的广告数据
	 * 参数:大广广告唯一标识id
	 * 返回值:List<AdItem>
	 */
	public List<AdItem> findContentAdList(Long categoryId);
}
