package com.twu.service.user_service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.twu.exception.UserException;
import com.twu.log.MyLogger;
import com.twu.model.user.UserModel;
import com.twu.repository.user_operate.IUserOperate;

import java.util.Scanner;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 16:25
 * @Description ***
 **/
public class UserServiceImp implements IUserService {

    private static Scanner scanner = new Scanner(System.in);

    @Named("userOperate")
    @Inject
    private IUserOperate userOperate;

    @Override
    public boolean voteHotSearchService(UserModel user)
            throws UserException {
        MyLogger.printMessage("请输入你要投票的热搜名称:");
        String name = scanner.nextLine();

        MyLogger.printMessage("请输入你要投票的热搜票数: (你目前还有: " + user.getVotesNumber() + "票)");
        String voteNumStr = scanner.nextLine();
        if (isNumberMatch(voteNumStr)) {
            // 是数字
            Integer voteNum = Integer.valueOf(voteNumStr);
            if (voteNum > user.getVotesNumber()) {
                // 剩余票数小于输入的投票数
                return false;
            } else {
                boolean result = userOperate.voteHotSearch(name, voteNum);
                if(result) {
                    // 更新用户剩余票数
                    user.setVotesNumber(user.getVotesNumber() - voteNum);
                }
                return result;
            }
        } else {
            throw new UserException("输入的投票数不是数字");
        }
    }

    //String name, Integer price, Integer degree
    @Override
    public boolean buyHotSearchService()
            throws UserException {
        MyLogger.printMessage("请输入你要购买的热搜名称:");
        String name = scanner.nextLine();
        MyLogger.printMessage("请输入你要购买的热搜排名:");
        String degreeStr = scanner.nextLine();
        MyLogger.printMessage("请输入你要购买的热搜金额:");
        String priceStr = scanner.nextLine();

        if (isNumberMatch(degreeStr) && isNumberMatch(priceStr)) {
            Double price = Double.valueOf(priceStr);
            Integer degree = Integer.valueOf(degreeStr);
            return userOperate.buyHotSearch(name, price, degree);
        } else {
            throw new UserException("输入的热搜排名或热搜金额不是数字");
        }
    }

    private boolean isNumberMatch(String input) {
        return input.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }
}
