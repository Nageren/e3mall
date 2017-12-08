package cn.e3.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.e3.manager.service.ItemCatService;
import cn.e3.mapper.TbItemCatMapper;
import cn.e3.pojo.TbItemCat;
import cn.e3.pojo.TbItemCatExample;
import cn.e3.pojo.TbItemCatExample.Criteria;
import cn.e3.utils.TreeNode;

public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper  itemCatMapper;
	
	/**
	 * 需求:根据父id查询次节点下的子节点
	 * 参数Long parentId
	 * 返回值List<TreeNode>
	 * [{
	 * 	"id":1,
	 *  "text":"Node 1",
	 *  "state":"closed"
	 * }]
	 */
	@Override
	public List<TreeNode> findItemCatTreeNodeList(Long parentId) {
		//创建List<TreeNode>
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		//创建 TbItemCatExample对象
		TbItemCatExample example = new TbItemCatExample();
		//创建criteria对象
		Criteria criteria = example.createCriteria();
		//设置查询参数
		criteria.andParentIdEqualTo(parentId);
		//List<TbItemCat>
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//循环商品类目集合对象 ,把商类目值封装到树形节点对象
		for (TbItemCat tbItemCat : list) {
			//创建单个树形节点对象
			TreeNode node = new TreeNode();
			//封装树形节点的值
			node.setId(tbItemCat.getId());
			//封装树形节点的名称
			node.setText(tbItemCat.getName());
			//封装树形节点的对象  1 closed  0 opne 
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			
			treeNodeList.add(node);
		}
		
		return treeNodeList;
	}

}
