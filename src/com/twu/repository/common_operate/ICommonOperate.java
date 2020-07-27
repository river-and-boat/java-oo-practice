package com.twu.repository.common_operate;

import com.twu.model.hot_search.HotSearchModel;

public interface ICommonOperate {

    // 查看热搜排行榜
    void viewSearchRankings();

    // 添加一个新的热搜
    void addHotSearch(HotSearchModel hotSearchModel);
}
