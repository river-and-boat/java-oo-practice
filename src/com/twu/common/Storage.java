package com.twu.common;

import com.twu.model.hot_search.HotSearchModel;
import java.util.HashMap;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 14:25
 * @Description ***
 **/
public class Storage {

    // 该Set用于存储非购买的热搜(自然排序热搜)
    public static HashMap<String, HotSearchModel> naturalHotSearchMap = new HashMap<>();

    // 该Set用于存储购买的热搜(按热搜等级排序)
    public static HashMap<String, HotSearchModel> purchaseHotSearchMap = new HashMap<>();
}
