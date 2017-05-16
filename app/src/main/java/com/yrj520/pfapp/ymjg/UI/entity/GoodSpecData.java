package com.yrj520.pfapp.ymjg.UI.entity;

import java.util.List;

/**
 * Created by zry on 17/5/16.
 */

public class GoodSpecData {

    /**
     * data : {"goods_id":"241","goods_name":"绣球蓝","market_price":"0.00","shop_price":"12.00","sumstore_count":"50","image_url":"/upload/goods_images/20170502/52d8302db72249c348f687ac8bf61879.jpg","spec":[],"goods_image":[],"attr":[{"attr_name":"颜色","attr_value":"蓝色\n"},{"attr_name":"等级","attr_value":"不限"},{"attr_name":"规格","attr_value":"不限"},{"attr_name":"产地","attr_value":"云南\n"},{"attr_name":"是否进口","attr_value":"否"},{"attr_name":"植物种类","attr_value":"绣球\n"},{"attr_name":"品牌","attr_value":"妖媚花坊"}],"goodsnum":0}
     * code : 200
     */

    private DataBean data;
    private String code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * goods_id : 241
         * goods_name : 绣球蓝
         * market_price : 0.00
         * shop_price : 12.00
         * sumstore_count : 50
         * image_url : /upload/goods_images/20170502/52d8302db72249c348f687ac8bf61879.jpg
         * spec : []
         * goods_image : []
         * attr : [{"attr_name":"颜色","attr_value":"蓝色\n"},{"attr_name":"等级","attr_value":"不限"},{"attr_name":"规格","attr_value":"不限"},{"attr_name":"产地","attr_value":"云南\n"},{"attr_name":"是否进口","attr_value":"否"},{"attr_name":"植物种类","attr_value":"绣球\n"},{"attr_name":"品牌","attr_value":"妖媚花坊"}]
         * goodsnum : 0
         */

        private String goods_id;
        private String goods_name;
        private String market_price;
        private String shop_price;
        private String sumstore_count;
        private String image_url;
        private int goodsnum;
        private List<?> spec;
        private List<?> goods_image;
        private List<AttrBean> attr;

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

        public String getSumstore_count() {
            return sumstore_count;
        }

        public void setSumstore_count(String sumstore_count) {
            this.sumstore_count = sumstore_count;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getGoodsnum() {
            return goodsnum;
        }

        public void setGoodsnum(int goodsnum) {
            this.goodsnum = goodsnum;
        }

        public List<?> getSpec() {
            return spec;
        }

        public void setSpec(List<?> spec) {
            this.spec = spec;
        }

        public List<?> getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(List<?> goods_image) {
            this.goods_image = goods_image;
        }

        public List<AttrBean> getAttr() {
            return attr;
        }

        public void setAttr(List<AttrBean> attr) {
            this.attr = attr;
        }

        public static class AttrBean {
            /**
             * attr_name : 颜色
             * attr_value : 蓝色

             */

            private String attr_name;
            private String attr_value;

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }
        }
    }
}
