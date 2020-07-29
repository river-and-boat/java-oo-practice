package com.twu.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.twu.repository.user_operate.IUserOperate;
import com.twu.repository.user_operate.UserOperateImp;
import com.twu.repository.common_operate.CommonOperateImp;
import com.twu.repository.common_operate.ICommonOperate;
import com.twu.repository.manager_operate.IManagerOperate;
import com.twu.repository.manager_operate.ManagerOperateImp;
import com.twu.service.common_service.CommonServiceImp;
import com.twu.service.common_service.ICommonService;
import com.twu.service.manager_service.IManagerService;
import com.twu.service.manager_service.ManagerServiceImp;
import com.twu.service.user_service.IUserService;
import com.twu.service.user_service.UserServiceImp;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/26 21:57
 * @Description 配置bean
 **/
public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        // 绑定仓储的依赖注入
        bind(ICommonOperate.class).annotatedWith(Names.named("commonOperate"))
                .to(CommonOperateImp.class);
        bind(IManagerOperate.class).annotatedWith(Names.named("managerOperate"))
                .to(ManagerOperateImp.class);
        bind(IUserOperate.class).annotatedWith(Names.named("userOperate"))
                .to(UserOperateImp.class);

        // 绑定服务的依赖注入
        bind(ICommonService.class).annotatedWith(Names.named("commonService"))
                .to(CommonServiceImp.class);
        bind(IManagerService.class).annotatedWith(Names.named("managerService"))
                .to(ManagerServiceImp.class);
        bind(IUserService.class).annotatedWith(Names.named("userService"))
                .to(UserServiceImp.class);
    }
}
