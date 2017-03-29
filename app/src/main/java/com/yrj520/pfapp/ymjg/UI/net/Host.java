package com.yrj520.pfapp.ymjg.UI.net;


public class Host {
    private static final int MODE_OFFICIAL = 0;//生产


    public static String HOST = "";//接口地址

    /**
     *生产环境下的地址
     */
    static {
        int MODE = MODE_OFFICIAL;
        switch (MODE) {
            case MODE_OFFICIAL:
                HOST = "http://pfapp.yrj520.com";
                break;
        }
    }

    //登录
    public  static final String URL_LOGIN=HOST+"/login";

     // 退出登录
    public  static final String URL_LOGOUT=HOST+"/login/logout";

    //主页
    public  static final String URL_INDEX=HOST+"/Homepage/index";

    //发送短信
    public  static final String URL_SENDMESSAGE=HOST+"/Register/sendVeryfiyCode";

    //注册提交
    public  static final String URL_REGISTER=HOST+"/Register/saveData";

    //直接获取1,2级分类
    public  static final String URL_GET_12GOODS_DIRECTLY=HOST+"/goodscategorylist/onetwoscate";

    //单独获取1,2级分类
    public  static final String URL_GET_12GOODS_SINGLELY=HOST+"/goodscategorylist/scategoryall";

    //获取3级分类
    public  static final String URL_GET_3GOODS=HOST+"/goodscategorylist/threescate";

    //获取商品下的规格商品
    public  static final String URL_GET_GOODS_SPEC=HOST+"/Goodscontroller/specgoodsall";

    //增加或减少规格商品
    public  static final String URL_OPERATE_GOODS_NUM=HOST+"/Goodscontroller/shoppingcartoperation";

    //生成订单
    public  static final String URL_PRODUCE_ORDER=HOST+"/Supplierorder/addorder";

    // 购物车总价总数量
    public  static final String URL_SHOPPING_CAT=HOST+"/Goodscategorylist/shopingcat";

    // 清空购物车
    public  static final String URL_CLEAR_SHOPPING_CAT=HOST+"/Supplierorder/emptyshopcat";

    //  订单列表
    public  static final String URL_ORDER_LIST=HOST+"/Supplierorder/orderlist";

    //  上传图片
    public  static final String URL_UPLOAD_IMAGE=HOST+"/Supplieruser/upload";

    //  查询个人资料
    public  static final String URL_QUERY_USERMESSAGE=HOST+"/Supplieruser/index";

    //  修改个人资料
    public  static final String URL_UPDATE_USERINFO=HOST+"/Supplieruser/usersave";

    //  找回密码
    public  static final String URL_FIND_PASSWORD=HOST+"/Register/operatepwd";

    //  地址管理
    public  static final String URL_ADDRESS_MANAGE=HOST+"/Supplieraddress/index";

    //  添加或者修改地址
    public  static final String URL_UPDATE_ADDRESS=HOST+"/Supplieraddress/addressmanage";

    //  设置默认地址
    public  static final String URL_SET_DEFAULT_ADDRESS=HOST+"/Supplieraddress/defaultaddre";

    //  查询默认地址
    public  static final String URL_QUERY_DEFAULT_ADDRESS=HOST+"/Supplieraddress/defaultaddress";

    //  删除地址
    public  static final String URL_DELETE_DEFAULT_ADDRESS=HOST+"/Supplieraddress/deleaddress";

    //  意见反馈
    public  static final String URL_FEEDBACKS=HOST+"/Feedbacks/index";

    //  修改订单状态
    public  static final String URL_UPDATE_ORDER_STATUS=HOST+"/Supplierorder/updateorderstatus";

    //  生成订单信息并构造支付请求
    public  static final String URL_ORDER_ALIPAY=HOST+"/Supplierorder/orderAlipay";

    //  付款完成之后的回调接口
    public  static final String URL_ORDER_AFTERPAY=HOST+"/Supplierorder/seleAlipay";
}
