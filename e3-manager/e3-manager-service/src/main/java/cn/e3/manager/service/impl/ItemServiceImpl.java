package cn.e3.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.manager.service.ItemService;
import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbContentExample;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemExample;
import cn.e3.utils.DataGridPagebean;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	
	public TbItem findItemById(Long itemId) {
		
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		
		return tbItem;
	}

	
	/**
	 * 需求:分页查询商品列表
	 * 参数:Integer page,Integer rows
	 * 返回值: dataGridPagebean
	 */
	@Override
	public DataGridPagebean findItemListByPage(Integer page, Integer rows) {
		//创建example
		TbItemExample example = new TbItemExample();
		//查询之前设置分页
		PageHelper.startPage(page,rows);
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		DataGridPagebean pagebean = new DataGridPagebean();
		//创建PageInfo,分页插件对象PageInfo封装了所有的
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//设置总记录数
		pagebean.setTotal(pageInfo.getTotal());
		//设置list
		pagebean.setRows(list); 
		//返回分页对象
		return pagebean;
	}

	
}
