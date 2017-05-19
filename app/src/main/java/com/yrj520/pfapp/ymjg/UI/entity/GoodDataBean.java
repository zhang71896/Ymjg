package com.yrj520.pfapp.ymjg.UI.entity;

/**
 * Created by zry on 17/5/19.
 */

public class GoodDataBean {

    /**
     * goods_id : 243
     * goods_name : 银叶菊
     * market_price : 0.00
     * shop_price : 10.00
     * image_url : /upload/goods_images/20170502/51c9d85ff2773d0d635b2ea61e2d5e63.jpg
     * goods_num :
     */

    private String goods_id;
    private String goods_name;
    private String market_price;
    private String shop_price;
    private String image_url;
    private String goods_num;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }
}
