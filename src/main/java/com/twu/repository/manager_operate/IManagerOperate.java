package com.twu.repository.manager_operate;

import com.twu.exception.UserException;
import com.twu.model.hot_search.HotSearchModel;

public interface IManagerOperate {

    /**
     * 添加超级热搜
     * @param hotSearchModel 超级热搜对象
     */
    boolean addSuperHotSearch(HotSearchModel hotSearchModel) throws UserException;
}
