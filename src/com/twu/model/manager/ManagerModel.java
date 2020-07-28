package com.twu.model.manager;

import com.twu.model.user.UserModel;

import java.util.HashMap;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 14:18
 * @Description ***
 **/
public class ManagerModel {

    // 管理员名
    private String name;

    // 账号类别
    private final static String TYPE = "管理员";

    public ManagerModel(String name) {
        this.name = name;
    }

    // 显示用户权限
    public void displayPermissionEnum() {
        permissionMap.entrySet()
                .stream()
                .parallel()
                .forEachOrdered(k -> System.out.println(k.getKey() + "   " + k.getValue()));
    }

    /**
     * 用户所拥有的权限
     **/
    private static final HashMap<Integer, String> permissionMap
            = new HashMap<Integer, String>() {
        {
            put(1,"查看热搜排行榜");
            put(2,"添加热搜");
            put(3,"添加超级热搜");
        }
    };
}