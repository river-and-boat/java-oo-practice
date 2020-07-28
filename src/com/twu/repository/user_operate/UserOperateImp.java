package com.twu.repository.user_operate;

import com.twu.common.Storage;
import com.twu.model.hot_search.HotSearchModel;

import java.util.Optional;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 15:52
 * @Description 用户操作
 **/
public class UserOperateImp implements IUserOperate {

    /**
     * 给热搜投票
     * @param name    热搜名
     * @param voteNum 投票数
     * @return 是否投票成功
     */
    @Override
    public boolean voteHotSearch(String name, Integer voteNum) {
        HotSearchModel hotSearchModel = Storage.naturalHotSearchMap.get(name);
        if (hotSearchModel != null) {
            Integer votesNum = hotSearchModel.getVotesNum();
            if (!hotSearchModel.getType()) {
                // 如果不是超级热搜，加一票
                hotSearchModel.setVotesNum(votesNum + voteNum);
            } else {
                // 超级热搜，一票顶两票
                hotSearchModel.setVotesNum(votesNum + 2 * voteNum);
            }
            return true;
        } else {
            Optional<HotSearchModel> model = Storage.purchaseHotSearchMap.values()
                    .stream()
                    .filter(s -> s.getName().equals(name))
                    .findFirst();
            // 不存在，则返回false
            if (!model.isPresent()) {
                return false;
            }
            hotSearchModel = model.get();
            if (hotSearchModel != null) {
                Integer votesNum = hotSearchModel.getVotesNum();
                if (!hotSearchModel.getType()) {
                    // 如果不是超级热搜，加一票
                    hotSearchModel.setVotesNum(votesNum + voteNum);
                } else {
                    // 超级热搜，一票顶两票
                    hotSearchModel.setVotesNum(votesNum + 2 * voteNum);
                }
                return true;
            }
            return false;
        }
    }

    /**
     * 用户够买热搜
     * @param name 热搜名
     * @param price 购买价钱
     * @param degree 热搜等级
     * @return
     */
    @Override
    public boolean buyHotSearch(String name, Double price, Integer degree) {
        // 判断naturalHotSearchMap中是否存在该事件
        HotSearchModel hotSearchModelInNatural = Storage.naturalHotSearchMap.get(name);
        if (hotSearchModelInNatural != null) {
            return setDegreeIfInNatural(hotSearchModelInNatural, price, degree);
        } else {
            // 查询该热搜是否已经在purchaseHotSearchMap中
            Optional<HotSearchModel> model = Storage.purchaseHotSearchMap.values()
                    .stream().filter(s -> s.getName().equals(name))
                    .findFirst();
            if (!model.isPresent()) {
                return false;
            }
            HotSearchModel hotSearchModelInPurchase = model.get();
            if (hotSearchModelInPurchase != null) {
                return setDegreeIfNotInNatural(hotSearchModelInPurchase, price, degree);
            } else {
                // 输入的热搜名不在队列中
                return false;
            }
        }
    }

    /**
     * 如果购买的热搜事件在naturalHotSearchMap队列中
     *
     * @param hotSearchModel 待购买的热搜事件
     * @param price          购买的价钱
     * @param degree         购买的热搜等级
     * @return 是否购买成功
     */
    private boolean setDegreeIfInNatural(HotSearchModel hotSearchModel, Double price, Integer degree) {
        // 首先查询对应的degree在purchaseHotSearchMap中是否存在
        // 获取当前在该热搜位的事件
        HotSearchModel hotPurchaseEvent = Storage.purchaseHotSearchMap.get(degree);
        // 如果degree位有热搜事件
        if (hotPurchaseEvent != null) {
            // 当前购买价格是否大于其本来的价格
            if (price > hotPurchaseEvent.getPurchasePrice()) {
                // 大于，可以购买
                // 购买过程包含：1.在purchaseHotSearchMap中替换掉热搜事件,2.在naturalHotSearchMap中删除掉热搜事件
                // 怎么保证一致性还未找到解决方案
                hotSearchModel.setPurchasePrice(price);
                hotSearchModel.setPurchaseHotSearch(true);
                hotSearchModel.setHotDegree(degree);
                Storage.purchaseHotSearchMap.put(degree, hotSearchModel);
                Storage.naturalHotSearchMap.remove(hotSearchModel.getName());
                return true;
            } else {
                // 钱不够，购买失败
                return false;
            }
        } else {
            // 如果degree位无热搜事件
            if (price > 0D) {
                hotSearchModel.setPurchasePrice(price);
                hotSearchModel.setPurchaseHotSearch(true);
                hotSearchModel.setHotDegree(degree);
                Storage.purchaseHotSearchMap.put(degree, hotSearchModel);
                Storage.naturalHotSearchMap.remove(hotSearchModel.getName());
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 此时待购买的热搜已存在于purchaseHotSearchMap队列中
     *
     * @param hotSearchModel 待购买热搜
     * @param price          购买价格
     * @param degree         购买等级
     * @return 是否购买成功
     */
    private boolean setDegreeIfNotInNatural(HotSearchModel hotSearchModel, Double price, Integer degree) {

        if (hotSearchModel.getHotDegree() >= degree) {
            // 如果购买的热搜排位比它现在的热搜排名低或相等，直接返回false
            return false;
        }
        // 得到待购买排位处的热搜
        HotSearchModel hotPurchaseEvent = Storage.purchaseHotSearchMap.get(degree);
        if (hotPurchaseEvent != null) {
            if (price > hotPurchaseEvent.getPurchasePrice()) {
                return setDegreeIfNotInNaturalTool(hotSearchModel, price, degree);
            } else {
                // 钱不够
                return false;
            }
        } else {
            // 如果degree位无热搜事件
            if (price > 0D) {
                return setDegreeIfNotInNaturalTool(hotSearchModel, price, degree);
            } else {
                return false;
            }
        }
    }

    /**
     * 如果购买的热搜事件已在purchaseHotSearchMap队列中，则需要在本队列中增加和删除
     *
     * @param hotSearchModel 待增加的热搜事件
     * @param price          热搜购买价格
     * @param degree         热搜等级
     * @return 是否购买成功
     */
    private boolean setDegreeIfNotInNaturalTool(HotSearchModel hotSearchModel,
                                                Double price, Integer degree) {
        Integer oldDegree = hotSearchModel.getHotDegree();
        hotSearchModel.setPurchasePrice(price);
        hotSearchModel.setPurchaseHotSearch(true);
        hotSearchModel.setHotDegree(degree);
        Storage.purchaseHotSearchMap.put(degree, hotSearchModel);
        Storage.purchaseHotSearchMap.remove(oldDegree);
        return true;
    }
}
