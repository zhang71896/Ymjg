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

public class ThridGoodsData {

    /**
     * data : [{"goods_id":"160","goods_name":"红宝石群生多头多肉植物","market_price":"6.00","shop_price":"5.00","image_url":"/upload/goods_images/20170407/c32114ee8a24b1568320d8ae9ccf74d3.jpg","goods_num":""},{"goods_id":"161","goods_name":"昂斯诺多肉植物","market_price":"3.00","shop_price":"2.00","image_url":"/upload/goods_images/20170407/cee9e3e3aee150b42dfe6e8d10f47b89.jpg","goods_num":""},{"goods_id":"162","goods_name":"黛比多肉植物","market_price":"3.00","shop_price":"2.00","image_url":"/upload/goods_images/20170407/ae0ba0593c85dd4d19fcf1a4e0fd4a39.jpg","goods_num":""},{"goods_id":"163","goods_name":"苔藓微景观办公室小盆栽","market_price":"40.00","shop_price":"38.00","image_url":"/upload/goods_images/20170407/b0868514e2231f35af4848d4866889d9.jpg","goods_num":""},{"goods_id":"164","goods_name":"苔藓微景观生态瓶破损花盆","market_price":"35.00","shop_price":"30.00","image_url":"/upload/goods_images/20170407/e8853de2aee67756a1467caceed09869.jpg","goods_num":""},{"goods_id":"165","goods_name":"苔藓微景观盆栽绿植盆","market_price":"63.00","shop_price":"59.00","image_url":"/upload/goods_images/20170407/3204997e63798acb3f600135e94705d7.jpg","goods_num":""},{"goods_id":"166","goods_name":"古树榕树盆景苗室内盆栽","market_price":"15.00","shop_price":"10.00","image_url":"/upload/goods_images/20170407/989d086b7f08d254fe7e4b80e2b8a094.jpg","goods_num":""},{"goods_id":"167","goods_name":"桌面盆栽绿植","market_price":"22.00","shop_price":"17.00","image_url":"/upload/goods_images/20170407/35a2028a726e8cbcece1dd81946b6a19.jpg","goods_num":""},{"goods_id":"168","goods_name":"铜钱草水培土培盆栽","market_price":"13.00","shop_price":"9.00","image_url":"/upload/goods_images/20170407/86dd0e2ec76a6ed98c71b59900148e23.jpg","goods_num":""},{"goods_id":"169","goods_name":"送礼首选大型室内花卉盆栽","market_price":"120.00","shop_price":"100.00","image_url":"/upload/goods_images/20170407/e1e5e582d5e802ab20939f26ad6d1e04.jpg","goods_num":""},{"goods_id":"170","goods_name":"室内桌面植物招财猪","market_price":"41.00","shop_price":"37.00","image_url":"/upload/goods_images/20170407/199b430d35d8cc86706929b07f4e6021.jpg","goods_num":""},{"goods_id":"171","goods_name":"盆栽绿萝吊兰","market_price":"31.00","shop_price":"26.00","image_url":"/upload/goods_images/20170407/7b50a295bf6d256b6b57630afc109ced.jpg","goods_num":""}]
     * code : 200
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
         * goods_id : 160
         * goods_name : 红宝石群生多头多肉植物
         * market_price : 6.00
         * shop_price : 5.00
         * image_url : /upload/goods_images/20170407/c32114ee8a24b1568320d8ae9ccf74d3.jpg
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
}
