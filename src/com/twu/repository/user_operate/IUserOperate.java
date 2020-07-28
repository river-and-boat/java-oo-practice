package com.twu.repository.user_operate;

public interface IUserOperate {

    /**
     * 给热搜事件投票
     * @param name 热搜名
     * @param voteNum 投票数
     */
    boolean voteHotSearch(String name, Integer voteNum);

    /**
     * 购买热搜
     * @param name 热搜名
     * @param price 购买价钱
     * @param degree 热搜等级
     */
    boolean buyHotSearch(String name, Double price, Integer degree);
}
