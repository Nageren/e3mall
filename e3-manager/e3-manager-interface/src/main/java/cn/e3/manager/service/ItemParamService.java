package cn.e3.manager.service;

import cn.e3.utils.E3mallResult;

/**
 * Created by Administrator on 2017/12/10.
 */
public interface ItemParamService {
    /**
     *  需求:根据商品分类的id去查询规格模版的信息
     *  参数:Long categoryId
     *  返回值:E3mallReseult.ok(ItemParam)
     */
    public E3mallResult findItemParamByCategoryId(Long  categoryId);
}
