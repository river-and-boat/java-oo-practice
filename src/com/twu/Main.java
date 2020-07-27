package com.twu;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.twu.config.ConfigModule;
import com.twu.model.hot_search.HotSearchModel;
import com.twu.service.common_service.ICommonService;
import com.twu.service.user_service.IUserService;

@Singleton
public class Main {

    @Inject
    @Named("userService")
    private IUserService userService;

    @Inject
    @Named("commonService")
    private ICommonService commonService;

    public static void main(String[] args) {
        // 导入依赖注入配置
        Injector injector = Guice.createInjector(new ConfigModule());
        // 获取主程序类
        Main mainBody = injector.getInstance(Main.class);

        mainBody.commonService.viewSearchRankingsService();


    }
}
