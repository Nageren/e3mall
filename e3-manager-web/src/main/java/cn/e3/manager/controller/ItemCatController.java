package cn.e3.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3.manager.service.ItemCatService;
import cn.e3.utils.TreeNode;

@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService  itemCatService;
	
	/**
	 * 需求:根据父id查询次节点下的子节点
	 * 请求:item/cat/list
	 * 参数Long parentId
	 * 返回值JSON List<TreeNode>
	 * [{
	 * 	"id":1,
	 *  "text":"Node 1",
	 *  "state":"closed"
	 * }]
	 * 
	 * 服务引用:一个类引用一次
	 * 1.参数问题:每次传递参数的树形节点是id,业务参数名称不匹配
	 * 2.初始化菜单,初始化菜单的顶级节点parentId = 0
	 */
	@RequestMapping("item/cat/list")
	@ResponseBody
	public List<TreeNode>  findItemCatTreeNodeList(@RequestParam(value="id",defaultValue="0")Long parentId){
		
		List<TreeNode> list = itemCatService.findItemCatTreeNodeList(parentId);
		
		return list;
	}
}
