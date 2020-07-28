package com.twu.common;

import com.twu.log.MyLogger;
import com.twu.model.manager.ManagerModel;
import com.twu.model.user.UserModel;

import java.util.HashMap;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 15:42
 * @Description ***
 **/
public class Tools {
    /**
     * 打印登录选项
     */
    public static void LoginReminder() {
        loginSelector.entrySet().stream()
                .parallel()
                .forEachOrdered(s -> MyLogger.printMessage(s.getKey(), s.getValue()));
    }

    /**
     * 返回或添加用户
     *
     * @param userName 用户名
     * @return 添加或已存在的用户
     */
    public static UserModel addOrGetUser(String userName) {
        UserModel user = Storage.userMap.get(userName);
        if (user == null) {
            user = new UserModel(userName);
            Storage.userMap.put(userName, user);
        }
        return user;
    }

    /**
     * 返回或者添加管理员
     *
     * @param managerName 管理员姓名
     * @return 添加或已存在的管理员
     */
    public static ManagerModel addOrGetManager(String managerName, String password) {
        ManagerModel manager = Storage.managerMap.get(managerName);
        if (manager == null) {
            manager = new ManagerModel(managerName, password);
            Storage.managerMap.put(managerName, manager);
            return manager;
        } else {
            if (password.equals(manager.getPassword())) {
                return manager;
            } else {
                return null;
            }
        }
    }

    /**
     * 打印系统的功能
     */
    public static void PrintLoginList() {
        loginSelector.entrySet().stream()
                .forEach(s -> MyLogger.printMessage(s.getKey(), s.getValue()));
    }

    /**
     * 登录选项设置
     */
    private static final HashMap<Integer, String> loginSelector
            = new HashMap<Integer, String>() {
        {
            put(1, "用户");
            put(2, "管理员");
            put(3, "退出");
        }
    };

}
