package com.twu.log;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 19:45
 * @Description ***
 **/
public class MyLogger {

    public static void printMessage(int id, String message) {
        System.out.println(id + "." + message);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * 显示热搜列表
     * @param id 编号
     * @param name 热搜名
     * @param votesNum 热搜投票数
     */
    public static void printHotSearch(int id, String name, int votesNum) {
        System.out.println(id + " " + name + " " +votesNum);
    }

    /**
     * 打印异常信息
     * @param exceptionMessage 异常信息
     */
    public static void printException(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
