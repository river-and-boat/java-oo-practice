package com.twu.repository.user_operate;

public interface IUserOperate {

    /**
     * 给热搜事件投票
     * @param 热搜名
     * @param 投票数
     */
    void voteHotSearch(String name, Integer voteNum);

    /**
     * 购买热搜
     * @param 热搜名
     * @param 购买价钱
     * @param 热搜等级
     */
    void buyHotSearch(String name, Integer price, Integer degree);
}
