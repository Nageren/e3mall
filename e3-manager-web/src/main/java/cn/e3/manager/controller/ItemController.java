package cn.e3.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3.manager.service.ItemService;
import cn.e3.pojo.TbItem;
import cn.e3.utils.DataGridPagebean;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("item/list/{itemId}")
	@ResponseBody
	public TbItem findItemBydId(@PathVariable Long itemId){
		
		TbItem itemById = itemService.findItemById(itemId);
		
		return itemById;
	}
	
	
	/**
	 * 需求:分页查询商品列表
	 * 参数:Integer page,Integer rows
	 * 返回值: dataGridPagebean
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public DataGridPagebean findItemListByPage(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="30") Integer rows){
		//远程调用service服务方法
		DataGridPagebean pagebean = itemService.findItemListByPage(page, rows);
		
		return pagebean;
	}
}
