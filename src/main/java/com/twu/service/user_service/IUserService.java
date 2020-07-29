package com.twu.service.user_service;

import com.twu.exception.UserException;
import com.twu.model.user.UserModel;

public interface IUserService {

    /**
     * 给热搜事件投票
     * @param user 当前用户
     */
    boolean voteHotSearchService(UserModel user) throws UserException;

    /**
     * 购买热搜
     */
    boolean buyHotSearchService() throws UserException;
}
