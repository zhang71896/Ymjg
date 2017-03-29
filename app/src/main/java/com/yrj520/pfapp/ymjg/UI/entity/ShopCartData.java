package com.yrj520.pfapp.ymjg.UI.entity;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */
//购物车返回的数据
public class ShopCartData {

    /**
     * sumprice : 9
     * goods_num : 2
     * data : [{"goods_num":"1","goods_id":"160","key_name":"是否含花盆:不含花盆 颜色:深黑色","price":"6.00","sgp_id":"138","store_count":"222"},{"goods_num":"1","goods_id":"161","key_name":"是否含花盆:不含花盆 颜色:深黑色","price":"3.00","sgp_id":"137","store_count":"444"}]
     * code : 200
     */

    private int sumprice;
    private String goods_num;
    private String code;
    private List<DataBean> data;

    public int getSumprice() {
        return sumprice;
    }

    public void setSumprice(int sumprice) {
        this.sumprice = sumprice;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goods_num : 1
         * goods_id : 160
         * key_name : 是否含花盆:不含花盆 颜色:深黑色
         * price : 6.00
         * sgp_id : 138
         * store_count : 222
         */

        private String goods_num;
        private String goods_id;
        private String key_name;
        private String price;
        private String sgp_id;
        private String store_count;

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getKey_name() {
            return key_name;
        }

        public void setKey_name(String key_name) {
            this.key_name = key_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSgp_id() {
            return sgp_id;
        }

        public void setSgp_id(String sgp_id) {
            this.sgp_id = sgp_id;
        }

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }
    }
}
