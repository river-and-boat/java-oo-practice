package com.twu;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.twu.common.Storage;
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

        HotSearchModel h3 = new HotSearchModel("测试3",false);

        HotSearchModel h1 = new HotSearchModel("测试1",true);
        h1.setHotDegree(3);
        h1.setPurchasePrice(10D);

        HotSearchModel h2 = new HotSearchModel("测试2",true);
        h1.setHotDegree(2);
        h1.setPurchasePrice(10D);

        mainBody.commonService.viewSearchRankingsService();
    }
}
