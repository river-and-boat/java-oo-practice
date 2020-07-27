package com.twu.service.manager_service;

import com.twu.model.hot_search.HotSearchModel;

public interface IManagerService {

    /**
     * 添加超级热搜
     * @param 超级热搜对象
     */
    void addSuperHotSearchService(HotSearchModel hotSearchModel);
}
