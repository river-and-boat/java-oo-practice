package com.twu.service.manager_service;

import com.twu.exception.UserException;

public interface IManagerService {

    /**
     * 添加超级热搜
     */
    boolean addSuperHotSearchService() throws UserException;
}
