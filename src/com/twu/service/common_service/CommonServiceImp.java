package com.twu.service.common_service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.twu.common.Storage;
import com.twu.exception.HotDegreeException;
import com.twu.model.hot_search.HotSearchModel;
import com.twu.repository.common_operate.ICommonOperate;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 16:26
 * @Description 通用热搜服务
 **/
public class CommonServiceImp implements ICommonService {

    @Inject
    @Named("commonOperate")
    private ICommonOperate commonOperate;

    /**
     * 查看热搜
     */
    @Override
    public void viewSearchRankingsService() {
        if (anyHotSearchEvent()) {
            // 有热搜，则查看
            commonOperate.viewSearchRankings();
        } else {
            return;
        }
    }

    /**
     * 添加热搜
     * @param hotSearchModel 待添加的热搜
     */
    @Override
    public void addHotSearchService(HotSearchModel hotSearchModel) {
        //首先判断热搜是否已经存在
        if (Storage.naturalHotSearchMap.keySet().contains(hotSearchModel.getName())) {
            throw new HotDegreeException("已存在该热搜");
        }

        if(hotSearchModel == null) {
            throw new HotDegreeException("添加的热搜为空");
        }

        if(hotSearchModel.getPurchaseHotSearch() == true
                || hotSearchModel.getPurchasePrice() > 0 || hotSearchModel.getHotDegree() > 0) {
            throw new HotDegreeException("添加的热搜不符合规范");
        }

        commonOperate.addHotSearch(hotSearchModel);
    }

    /**
     * 查看是否有热搜事件
     * @return true: 有热搜; falseL: 没有热搜
     */
    private boolean anyHotSearchEvent() {
        return Storage.naturalHotSearchMap.size() > 0
                || Storage.purchaseHotSearchMap.size() > 0;
    }
}
