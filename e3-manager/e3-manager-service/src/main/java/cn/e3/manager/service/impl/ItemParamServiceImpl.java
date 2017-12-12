package cn.e3.manager.service.impl;

import cn.e3.manager.service.ItemParamService;
import cn.e3.mapper.TbItemParamMapper;
import cn.e3.pojo.TbItemParam;
import cn.e3.pojo.TbItemParamExample;
import cn.e3.utils.E3mallResult;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Administrator on 2017/12/10.
 */
public class ItemParamServiceImpl  implements ItemParamService{

    @Autowired
    private TbItemParamMapper itemParamMapper;



    /**
     *  需求:根据商品分类的id去查询规格模版的信息
     *  参数:Long categoryId
     *  返回值:E3mallReseult.ok(ItemParam)
     */
    @Override
    public E3mallResult findItemParamByCategoryId(Long categoryId) {
        //创建exmaple对象
        TbItemParamExample example = new TbItemParamExample();
        //创建Criteria对象
        TbItemParamExample.Criteria criteria = example.createCriteria();
        //设置查询调价
        //设置查询参数:根据分类id查询商品的规格模版
        criteria.andItemCatIdEqualTo(categoryId);
        //执行查询
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        TbItemParam itemParam = null;
        //判断是否关联了模版
        if (list != null && list.size()>0){
            itemParam = list.get(0);
        }
        //设置返回值
        return E3mallResult.ok(itemParam);
    }
}
