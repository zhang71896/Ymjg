package com.yrj520.pfapp.ymjg.UI.event;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class CartRefreshEvent {
    //购物车刷新事件
    private String mMsg;

    private int totalCount;
    private float totalPrices;
    public CartRefreshEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public float getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(float totalPrices) {
        this.totalPrices = totalPrices;
    }


    public String getMsg(){
        return mMsg;
    }


}
