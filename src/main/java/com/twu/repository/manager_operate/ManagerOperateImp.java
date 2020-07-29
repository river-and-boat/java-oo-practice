package com.twu.repository.manager_operate;

import com.twu.common.Storage;
import com.twu.exception.UserException;
import com.twu.model.hot_search.HotSearchModel;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 15:53
 * @Description 添加超级热搜
 **/
public class ManagerOperateImp implements IManagerOperate {
    @Override
    public boolean addSuperHotSearch(HotSearchModel hotSearchModel)
            throws UserException {
        try {
            Storage.naturalHotSearchMap.put(hotSearchModel.getName(), hotSearchModel);
            return true;
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
    }
}
