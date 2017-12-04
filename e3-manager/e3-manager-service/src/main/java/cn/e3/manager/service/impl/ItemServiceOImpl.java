package cn.e3.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3.manager.service.ItemService;
import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbItem;

@Service
public class ItemServiceOImpl implements ItemService {

	@Autowired
	private TbItemMapper mapper;
	
	
	public TbItem findItemById(Long itemId) {
		
		TbItem tbItem = mapper.selectByPrimaryKey(itemId);
		
		return tbItem;
	}

}
