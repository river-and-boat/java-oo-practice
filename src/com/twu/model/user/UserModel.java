package com.twu.model.user;

import com.twu.log.MyLogger;

import java.util.HashMap;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 14:20
 * @Description ***
 **/
public class UserModel {

    // 用户名
    private String name;

    //用户剩余的投票数，初始值为10
    private Integer votesNumber = 10;

    // 类别
    private final static String TYPE = "普通用户";

    public UserModel(String name) {
        this.name = name;
    }

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVotesNumber() {
        return votesNumber;
    }

    public void setVotesNumber(Integer votesNumber) {
        this.votesNumber = votesNumber;
    }

    // 显示用户权限
    public void displayPermissionEnum() {
        permissionMap.entrySet()
                .stream()
                .parallel()
                .forEachOrdered(k -> MyLogger.printMessage(k.getKey(), k.getValue()));
    }

    /**
     * 用户所拥有的权限
     **/
    private static final HashMap<Integer, String> permissionMap
            = new HashMap<Integer, String>() {
        {
            put(1, "查看热搜排行榜");
            put(2, "给热搜事件投票");
            put(3, "购买热搜");
            put(4, "添加热搜");
            put(5, "退出");

        }
    };
}