package com.yrj520.pfapp.ymjg.UI.entity;

import java.util.List;

/**
 * Created by zry on 17/5/19.
 */

public class ThridAllGood {

    /**
     * data : []
     * code : 200
     * goodsdata : [{"goods_id":"243","goods_name":"银叶菊","market_price":"0.00","shop_price":"10.00","image_url":"/upload/goods_images/20170502/51c9d85ff2773d0d635b2ea61e2d5e63.jpg","goods_num":""},{"goods_id":"245","goods_name":"叶上花","market_price":"0.00","shop_price":"15.00","image_url":"/upload/goods_images/20170502/80f5b9b4e35ad8d3802cf27417e700f8.jpg","goods_num":""},{"goods_id":"246","goods_name":"水晶草","market_price":"0.00","shop_price":"17.00","image_url":"/upload/goods_images/20170502/ee9dc89c6fbfa8df193d86a66d7015b7.jpg","goods_num":""},{"goods_id":"248","goods_name":"尤加利大","market_price":"0.00","shop_price":"50.00","image_url":"/upload/goods_images/20170502/f5282214e204db66515d5240723fae1a.jpg","goods_num":""},{"goods_id":"249","goods_name":"尤加利小","market_price":"0.00","shop_price":"40.00","image_url":"/upload/goods_images/20170502/c901a0b9cdd7c20f1768dd054422f7ac.jpg","goods_num":""},{"goods_id":"297","goods_name":"Test","market_price":"0.00","shop_price":"1.00","image_url":"/upload/goods_images/20170516/a4468cebfb9eff7cdbf694d4c01083aa.jpg","goods_num":""}]
     */

    private String code;
    private List<?> data;
    private List<GoodDataBean> goodsdata;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public List<GoodDataBean> getGoodsdata() {
        return goodsdata;
    }

    public void setGoodsdata(List<GoodDataBean> goodsdata) {
        this.goodsdata = goodsdata;
    }


}
