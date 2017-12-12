package cn.e3.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import cn.e3.content.service.ContentCategoryService;
import cn.e3.mapper.TbContentCategoryMapper;
import cn.e3.pojo.TbContentCategory;
import cn.e3.pojo.TbContentCategoryExample;
import cn.e3.pojo.TbContentCategoryExample.Criteria;
import cn.e3.utils.E3mallResult;
import cn.e3.utils.TreeNode;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	//注入mapper接口代理对象
	@Autowired
	private TbContentCategoryMapper  contentCategoryMapper;
	
	/**
	 * 需求:根据父id查询下面的子节点
	 * 参数:Long parentId
	 * 返回值:List<TreeNode>
	 * 请求:/content/category/list
	 * 注意服务是否发布?
	 */
	@Override
	public List<TreeNode> findContentTreeNodeList(Long parentId) {
		//创建树形节点的集合List<TreeNode>,封装属性节点
		List<TreeNode> treeNodeList = new ArrayList<>();
		
		//创建example对象
		TbContentCategoryExample example = new TbContentCategoryExample();
		//创建createCriteria对象
		Criteria createCriteria = example.createCriteria();
		//设置查询参数,根据父id查询节点下面的子节点
		createCriteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> catelist = contentCategoryMapper.selectByExample(example);
		//循环分类表的集合数据,把分类数据封装到treeNodeList
		for (TbContentCategory tbContentCategory : catelist) {
			//创建属性节点的对象,封装属性
			TreeNode node = new TreeNode();
			//封装id
			node.setId(tbContentCategory.getId());
			//封装名称
			node.setText(tbContentCategory.getName());
			//封装状态
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//把节点对象放入几点集合
			treeNodeList.add(node);
		}
		
		return treeNodeList;
	}

	
	/**
	 * 需求:新建属性节点
	 * 参数:Long parentId ,String name  新建节点的父id(就是父节点的id),节点的名称
	 * 返回值:E3mallResult
	 * 
	 * 业务分析:
	 * 	1.如果新建节点的父节点是子节点,修改父节点的状态
	 * 		 新建节点的父id(就是父节点的id),根据父节点的id(parentId)查询父节点对象
	 *  2.新建节点的父节点原来是父节点,直接添加节点即可
	 */
	@Override
	public E3mallResult createNode(Long parentId, String name) {
		//创建一个广告分类对象
		TbContentCategory contentCategory = new TbContentCategory();
		//封装分类属性
		//设置父id
		contentCategory.setParentId(parentId);
		//设置树形节点的名称
		contentCategory.setName(name);
		//设置status 1 正常  2 删除
		contentCategory.setStatus(1);
		//设置  Integer sortOrder
		contentCategory.setSortOrder(1);
		//新建的节点一定是子节点
		contentCategory.setIsParent(false);
		//Date 数据
		Date date = new Date();
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		//1.如果新建节点的父节点是子节点,修改父节点的状态
		//2.新建节点的父节点原来是父节点,直接添加节点即可
		//新建节点的parentId,是父节点的id
		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		//在这里需要判断父节点是不是子节点   如果父节点是子节点
		if(!contentCategory.getIsParent()){
			// 如果父节点是子节点   添加节点后需要 设置父节点的状态为 父节点
			tbContentCategory.setIsParent(true);
			// 修改
			contentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
		}
		
		//执行保存
		contentCategoryMapper.insert(contentCategory);
		//返回节点对象的id
		return E3mallResult.ok(contentCategory);
	}

	
	/**
	 * 重命名
	 * 
	 * 删除:
	 * 如果这个节点是子节点,才可以删除,如果是父节点不能删除
	 * 如果删除节点的父节点只有一个子节点,删除后父节点b
	 * 
	 */
}
