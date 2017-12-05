package cn.e3.manager.service;

import cn.e3.pojo.TbItem;
import cn.e3.utils.DataGridPagebean;

public interface ItemService {
	/**
	 * 需求:查询所有的商品进行展示
	 * 
	 * 根据id查询商品数据
	 * 
	 */
	public TbItem findItemById(Long itemId);
	
	/**
	 * 需求:分页查询商品列表
	 * 参数:Integer page,Integer rows
	 * 返回值: dataGridPagebean
	 */
	public DataGridPagebean findItemListByPage(Integer page,Integer rows); 
}
