package com.twu.service.user_service;

public interface IUserService {

    /**
     * 给热搜事件投票
     * @param 热搜名
     * @param 投票数
     */
    void voteHotSearchService(String name, Integer voteNum);

    /**
     * 购买热搜
     * @param 热搜名
     * @param 购买价钱
     * @param 热搜等级
     */
    void buyHotSearchService(String name, Integer price, Integer degree);
}
