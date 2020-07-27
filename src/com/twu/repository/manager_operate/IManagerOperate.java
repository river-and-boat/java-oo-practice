package com.twu.repository.manager_operate;

import com.twu.model.hot_search.HotSearchModel;

public interface IManagerOperate {

    /**
     * 添加超级热搜
     * @param 超级热搜对象
     */
    void addSuperHotSearch(HotSearchModel hotSearchModel);
}
