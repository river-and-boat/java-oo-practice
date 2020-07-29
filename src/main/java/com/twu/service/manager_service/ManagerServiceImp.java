package com.twu.service.manager_service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.twu.exception.UserException;
import com.twu.log.MyLogger;
import com.twu.model.hot_search.HotSearchModel;
import com.twu.common.Storage;
import com.twu.repository.manager_operate.IManagerOperate;

import java.util.Scanner;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 16:25
 * @Description ***
 **/
public class ManagerServiceImp implements IManagerService {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Inject
    @Named("managerOperate")
    private IManagerOperate managerOperate;

    @Override
    public boolean addSuperHotSearchService()
            throws UserException {
        MyLogger.printMessage("请输入您要添加的超级热搜事件的名字:");
        String hotSearchName = SCANNER.nextLine();
        if(!Storage.naturalHotSearchMap.keySet().contains(hotSearchName)) {
            HotSearchModel hotSearchModel = new HotSearchModel(hotSearchName);
            // 设置其为超级热搜
            hotSearchModel.setType(true);
            return managerOperate.addSuperHotSearch(hotSearchModel);
        } else {
            return false;
        }
    }
}
