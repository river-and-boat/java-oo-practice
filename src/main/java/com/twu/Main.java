package com.twu;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.twu.common.Tools;
import com.twu.config.ConfigModule;
import com.twu.exception.HotDegreeException;
import com.twu.exception.UserException;
import com.twu.log.MyLogger;
import com.twu.model.manager.ManagerModel;
import com.twu.model.user.UserModel;
import com.twu.service.common_service.ICommonService;
import com.twu.service.manager_service.IManagerService;
import com.twu.service.user_service.IUserService;

import java.util.Scanner;

@Singleton
public class Main {

    //用户接口注入
    @Inject
    @Named("userService")
    private IUserService userService;

    //通用接口注入
    @Inject
    @Named("commonService")
    private ICommonService commonService;

    //管理员接口注入
    @Inject
    @Named("managerService")
    private IManagerService managerService;

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        // 导入依赖注入配置
        Injector injector = Guice.createInjector(new ConfigModule());
        // 获取主程序类
        Main mainBody = injector.getInstance(Main.class);
        // 控制第一层循环
        String mainPage = "zero";
        while (!mainPage.equals("3")) {
            Tools.LoginReminder();
            mainPage = SCANNER.nextLine();
            switch (mainPage) {
                case "1":
                    // 用户登录接口调用
                    UserLogin(mainBody);
                    break;
                case "2":
                    // 管理员登录接口调用
                    ManagerLogin(mainBody);
                    break;
                case "3":
                    //退出
                    break;
                default:
                    MyLogger.printMessage("请输入正确的选项");
                    break;
            }
        }
    }

    /**
     * 用户登录的系列操作
     */
    private static void UserLogin(Main mainBody) {
        MyLogger.printMessage("请输入您的昵称:");
        UserModel user = Tools.addOrGetUser(SCANNER.nextLine());
        // 控制user层的循环
        String userPage = "zero";
        while (!userPage.equals("5")) {
            MyLogger.printMessage("您好, " + user.getName() + ", 您可以：");
            //打印用户权限，后期修改可以动态绑定权限，目前暂时写死
            user.displayPermissionEnum();
            userPage = SCANNER.nextLine();
            try {
                switch (userPage) {
                    case "1":
                        //查看热搜排行榜
                        mainBody.commonService.viewSearchRankingsService();
                        break;
                    case "2":
                        //给热搜事件投票
                        if (!mainBody.userService.voteHotSearchService(user)) {
                            MyLogger.printMessage("投票失败");
                        } else {
                            MyLogger.printMessage("投票成功");
                        }
                        break;
                    case "3":
                        //够买热搜
                        if (!mainBody.userService.buyHotSearchService()) {
                            MyLogger.printMessage("购买热搜失败");
                        } else {
                            MyLogger.printMessage("购买热搜成功");
                        }
                        break;
                    case "4":
                        //添加热搜
                        if (!mainBody.commonService.addHotSearchService()) {
                            MyLogger.printMessage("添加热搜失败");
                        } else {
                            MyLogger.printMessage("添加热搜成功");
                        }
                        break;
                    case "5":
                        //退出
                        break;
                    default:
                        MyLogger.printMessage("请输入正确的选项");
                        break;
                }
            } catch (UserException userException) {
                MyLogger.printException(userException.getMessage());
            } catch (HotDegreeException hotDegreeException) {
                MyLogger.printException(hotDegreeException.getMessage());
            }
        }
    }

    /**
     * 管理员登录的系列操作
     */
    private static void ManagerLogin(Main mainBody) {
        MyLogger.printMessage("请输入您的昵称:");
        String managerName = SCANNER.nextLine();
        MyLogger.printMessage("请输入您的密码:");
        String password = SCANNER.nextLine();
        ManagerModel manager = Tools.addOrGetManager(managerName, password);
        if (manager == null) {
            MyLogger.printMessage("输入密码错误");
            return;
        } else {
            //控制manager层的循环
            String managerPage = "zero";
            while (!managerPage.equals("4")) {
                MyLogger.printMessage("您好, " + manager.getName() + ", 您可以:");
                //显示管理员的权限，后期可动态绑定
                manager.displayPermissionEnum();
                managerPage = SCANNER.nextLine();

                try {
                    switch (managerPage) {
                        case "1":
                            //查看热搜排行榜
                            mainBody.commonService.viewSearchRankingsService();
                            break;
                        case "2":
                            //添加热搜
                            if (!mainBody.commonService.addHotSearchService()) {
                                MyLogger.printMessage("添加热搜失败");
                            } else {
                                MyLogger.printMessage("添加热搜成功");
                            }
                            break;
                        case "3":
                            //添加超级热搜
                            if (!mainBody.managerService.addSuperHotSearchService()) {
                                MyLogger.printMessage("添加超级热搜失败");
                            } else {
                                MyLogger.printMessage("添加超级热搜成功");
                            }
                            break;
                        case "4":
                            //退出
                            break;
                        default:
                            MyLogger.printMessage("请输入正确的选项");
                            break;
                    }
                } catch (UserException userException) {
                    MyLogger.printException(userException.getMessage());
                } catch (HotDegreeException hotDegreeException) {
                    MyLogger.printException(hotDegreeException.getMessage());
                }
            }
        }
    }
}
