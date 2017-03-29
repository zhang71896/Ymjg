package com.yrj520.pfapp.ymjg.UI.entity;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * 生成的订单实体
 * @author Rock
 * @version 1.0
 */

public class OrderData {

    /**
     * code : 200
     * data : [{"order_id":"48","ordernumber":"1491987332194386","money":"155.00","add_time":"2017-04-12 16:55:32","order_status":"0","pay_status":"0","auditings":"0","spec":[{"goods_num":"1","store_count":"569","goods_price":"155.00","goods_name":"酒店餐桌花仿真花会议圆形桌花","key_name":"是否含花盆:不含花盆 颜色:深黑色","original_img":"/upload/goods_images/20170407/3f3c52dced847ba6f85760ad909b0dd1.jpg","sumprice":"155","specimg":[]}]}]
     */

    private String code;
    private List<DataBean> data;

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
         * order_id : 48
         * ordernumber : 1491987332194386
         * money : 155.00
         * add_time : 2017-04-12 16:55:32
         * order_status : 0
         * pay_status : 0
         * auditings : 0
         * spec : [{"goods_num":"1","store_count":"569","goods_price":"155.00","goods_name":"酒店餐桌花仿真花会议圆形桌花","key_name":"是否含花盆:不含花盆 颜色:深黑色","original_img":"/upload/goods_images/20170407/3f3c52dced847ba6f85760ad909b0dd1.jpg","sumprice":"155","specimg":[]}]
         */

        private String order_id;
        private String ordernumber;
        private String money;
        private String add_time;
        private String order_status;
        private String pay_status;
        private String auditings;
        private List<SpecBean> spec;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getAuditings() {
            return auditings;
        }

        public void setAuditings(String auditings) {
            this.auditings = auditings;
        }

        public List<SpecBean> getSpec() {
            return spec;
        }

        public void setSpec(List<SpecBean> spec) {
            this.spec = spec;
        }

        public static class SpecBean {
            /**
             * goods_num : 1
             * store_count : 569
             * goods_price : 155.00
             * goods_name : 酒店餐桌花仿真花会议圆形桌花
             * key_name : 是否含花盆:不含花盆 颜色:深黑色
             * original_img : /upload/goods_images/20170407/3f3c52dced847ba6f85760ad909b0dd1.jpg
             * sumprice : 155
             * specimg : []
             */

            private String goods_num;
            private String store_count;
            private String goods_price;
            private String goods_name;
            private String key_name;
            private String original_img;
            private String sumprice;
            private List<?> specimg;

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getKey_name() {
                return key_name;
            }

            public void setKey_name(String key_name) {
                this.key_name = key_name;
            }

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getSumprice() {
                return sumprice;
            }

            public void setSumprice(String sumprice) {
                this.sumprice = sumprice;
            }

            public List<?> getSpecimg() {
                return specimg;
            }

            public void setSpecimg(List<?> specimg) {
                this.specimg = specimg;
            }
        }
    }
}
