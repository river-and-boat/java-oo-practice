package com.twu.repository.common_operate;
import com.twu.exception.UserException;
import com.twu.log.MyLogger;
import com.twu.model.hot_search.HotSearchModel;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import static com.twu.common.Storage.naturalHotSearchMap;
import static com.twu.common.Storage.purchaseHotSearchMap;

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

        // 第一个集合为购买热搜集合，第二个热搜为自然热搜集合
        // 将两个热搜的列表综合显示排序
        int hotItemCount = purchaseHotSearchMap.size() + naturalHotSearchMap.size();
        // 定义结果数组
        HotSearchModel[] resultArray = new HotSearchModel[hotItemCount];
        // 定义LinedList存储键
        LinkedList<Integer> keysList = new LinkedList<>();
        for (Integer i = 0; i < hotItemCount; i++) {
            keysList.add(i);
        }

        // 首先将购买热搜列表存入到相应的位置上
        purchaseHotSearchMap.entrySet()
                .stream().sorted(Map.Entry.comparingByKey())
                .forEach(s -> {
                    Integer index = s.getKey() - 1;
                    // 删除keyList中已使用的键
                    keysList.remove(index);
                    resultArray[s.getKey() - 1] = s.getValue();
                });

        // 接下来将自然热搜榜的热搜列表存入数组中，以keyList的剩余值为键，按顺序存入
        naturalHotSearchMap.values()
                .stream()
                .sorted(Comparator.comparingInt(HotSearchModel::getVotesNum).reversed())
                .forEach(s -> resultArray[keysList.pop()] = s);

        for (HotSearchModel hotSearch : resultArray) {
            // 打印日志
            if (hotSearch != null) {
                MyLogger.printHotSearch(++count, hotSearch.getName(), hotSearch.getVotesNum());
            }
        }
    }

    /**
     * 增加一个热搜
     * 增加的热搜统一采用默认形式，所以添加到naturalHotSearchSet中
     *
     * @param hotSearchModel 待添加的热搜
     */
    @Override
    public boolean addHotSearch(HotSearchModel hotSearchModel)
            throws UserException {
        // 添加自然热搜
        try {
            naturalHotSearchMap.put(hotSearchModel.getName(), hotSearchModel);
            return true;
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
    }
}
