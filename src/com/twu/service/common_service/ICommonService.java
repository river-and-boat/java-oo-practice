package com.twu.service.common_service;

import com.twu.model.hot_search.HotSearchModel;

public interface ICommonService {

    // 查看热搜排行榜
    void viewSearchRankingsService();

    // 添加一个新的热搜
    void addHotSearchService(HotSearchModel hotSearchModel);
}
