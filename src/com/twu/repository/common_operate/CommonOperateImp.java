package com.twu.repository.common_operate;

import com.sun.javafx.collections.MappingChange;
import com.twu.common.Storage;
import com.twu.log.MyLogger;
import com.twu.model.hot_search.HotSearchModel;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 15:54
 * @Description ***
 **/
public class CommonOperateImp implements ICommonOperate {

    /**
     * 查看当前的实时排行，需要融合两个列表的结果
     * 购买的热搜列表始终在自然列表之前
     */
    @Override
    public void viewSearchRankings() {
        // 为了对排名进行统计
        int count = 0;

        // 第一个集合为购买热搜集合，在前面，以热度排名进行排序
        // 第二个热搜为自然热搜集合，在后面，以投票数进行排序

        List<HotSearchModel> resultList = Stream.concat(
                Storage.purchaseHotSearchMap.entrySet()
                        .stream().sorted(Map.Entry.comparingByKey())
                        .map(s -> s.getValue()),
                Storage.naturalHotSearchMap.values()
                        .stream()
                        .sorted(Comparator.comparingInt(HotSearchModel::getVotesNum).reversed())
        ).collect(Collectors.toList());

        for (HotSearchModel hotSearch : resultList) {
            // 打印日志
            MyLogger.printHotSearch(++count, hotSearch.getName(), hotSearch.getVotesNum());
        }
    }

    /**
     * 增加一个热搜
     * 增加的热搜统一采用默认形式，所以添加到naturalHotSearchSet中
     *
     * @param hotSearchModel 待添加的热搜
     */
    @Override
    public boolean addHotSearch(HotSearchModel hotSearchModel) {
        // 添加自然热搜
        HotSearchModel putModel = Storage.naturalHotSearchMap.put(hotSearchModel.getName(), hotSearchModel);
        if(putModel != null) {
            return true;
        }
        return false;
    }
}
