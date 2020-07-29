package com.twu.service.common_service;

import com.twu.exception.HotDegreeException;
import com.twu.exception.UserException;

public interface ICommonService {

    // 查看热搜排行榜
    void viewSearchRankingsService();

    // 添加一个新的热搜
    boolean addHotSearchService() throws HotDegreeException, UserException;
}
