package com.twu.model.hot_search;

import com.twu.exception.HotDegreeException;

import java.util.Comparator;

/**
 * @Auto Jiang Yuzhou
 * @Date 2020/7/27 15:00
 * @Description ***
 **/
public class HotSearchModel {

    private Integer id;

    private String name;

    private Integer votesNum = 0;

    // 表明是否为超级热搜(true:是;false:不是)，默认不是
    private Boolean type = false;

    // 表明热搜是否为购买的热搜(true:是;false:不是)，默认不是
    private Boolean purchaseHotSearch = false;

    // 该热搜的购买价格，默认为非购买热搜，价钱为-1
    private Double purchasePrice = -1.0;

    // 该热搜购买的等级，默认为非购买热搜，等级为-1
    private Integer hotDegree = -1;

    public HotSearchModel(String name, Boolean purchaseHotSearch) {
        this.name = name;
        this.purchaseHotSearch = purchaseHotSearch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVotesNum() {
        return votesNum;
    }

    public void setVotesNum(Integer votesNum) {
        this.votesNum = votesNum;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getPurchaseHotSearch() {
        return purchaseHotSearch;
    }

    public void setPurchaseHotSearch(Boolean purchaseHotSearch) {
        this.purchaseHotSearch = purchaseHotSearch;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getHotDegree() {
        return hotDegree;
    }

    public void setHotDegree(Integer hotDegree) {
        this.hotDegree = hotDegree;
    }
}
