package com.yrj520.pfapp.ymjg.UI.api;

import android.content.Context;

import com.yrj520.pfapp.ymjg.UI.config.Constant;
import com.yrj520.pfapp.ymjg.UI.net.Host;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil.RequestBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class UserApi {


    /**
     * 登录接口
     * @param context 上下文对象
     * @param phone 手机号码
     * @param password 密码
     * @param onBack 回调函数
     */
    public static void LoginApi(Context context,String phone,String password, RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", String.valueOf(password));
        HttpUtil.doGet(context, Constant.REQUEST_ID_RETURN, Host.URL_LOGIN, params, onBack);
    }

    /**
     * 退出登录接口
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void LogOutApi(Context context,RequestBack onBack){
        HttpUtil.doGet(context, Constant.REQUEST_ID_RETURN, Host.URL_LOGOUT, null, onBack);
    }

    /**
     * 访问主页接口
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void IndexApi(Context context,RequestBack onBack){
        HttpUtil.doGet(context, Constant.REQUEST_ID_RETURN, Host.URL_INDEX, null, onBack);
    }

    /**
     * 发送短信接口
     * @param context 上下文对象
     * @param phone 手机号码
     * @param scene 场景 1:注册 2:找回和修改
     * @param onBack 回调函数
     */
    public static void SendMessageApi(Context context,String phone,String scene,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("scene", scene);
        HttpUtil.doGet(context, Constant.REQUEST_ID_RETURN, Host.URL_SENDMESSAGE, params, onBack);
    }

    /**
     * 注册提交接口
     * @param context 上下文对象
     * @param phone 手机号码
     * @param password 密码
     * @param captcha 验证码
     */
    public static void Register(Context context,String phone,String password,String captcha,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", password);
        params.put("captcha",captcha);
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_REGISTER,params,onBack);
    }

    /**
     * 直接获取1,2级分类
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void Get12GoodsDirectlyApi(Context context,RequestBack onBack){
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_GET_12GOODS_DIRECTLY,null,onBack);
    }

    /**
     * 单独获取1,2级分类
     * @param context 上下文对象
     * @param parent_id 商品分类id
     * @param onBack 回调函数
     */
    public static void Get12GoodsSinglyApi(Context context,String parent_id,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("parent_id", parent_id);
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_GET_12GOODS_SINGLELY,params,onBack);
    }

    /**
     * 获取3级分类
     * @param context 上下文对象
     * @param parent_id 商品分类id
     * @param onBack 回调函数
     */
    public static  void Get3GoodsApi(Context context,String parent_id,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("parent_id", parent_id);
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_GET_3GOODS,params,onBack);
    }

    /**
     * 获取商品下的规格商品
     * @param context 上下文对象
     * @param goods_id 商品id
     * @param onBack 回调函数
     */
    public static void GetGoodsSpecApi(Context context,String goods_id,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("goods_id",goods_id);
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_GET_GOODS_SPEC,params,onBack);
    }

    /**
     * 增加或减少规格商品
     * @param context 上下文对象
     * @param goods_id 商品id
     * @param sgp_id 规格id
     * @param goods_num 该规格商品当前数量
     * @param onBack 回调函数
     */
    public static void OperateGoodsNumApi(Context context,String goods_id,String sgp_id,String goods_num,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("goods_id",goods_id);
        params.put("sgp_id",sgp_id);
        params.put("goods_num",goods_num);
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_OPERATE_GOODS_NUM,params,onBack);
    }

    /**
     * 生成订单
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void ProduceOrderApi(Context context,RequestBack onBack){
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_PRODUCE_ORDER,null,onBack);
    }

    /**
     * 购物车总价总数量
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void ShoppingCatApi(Context context,RequestBack onBack){
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_SHOPPING_CAT,null,onBack);
    }

    /**
     * 清空购物车
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void ClearShoppingCatApi(Context context,RequestBack onBack){
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_CLEAR_SHOPPING_CAT,null,onBack);
    }

    /**
     * 订单列表
     * @param context 上下文对象
     * @param order_status $order_status=0 待付款  $order_status=1 全部   $order_status=2 待收货  $order_status=3 已取消 $order_status=4 已完成
     * @param onBack 回调函数
     */
    public static void OrderListApi(Context context,String order_status,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("order_status",order_status);
        HttpUtil.doGet(context,Constant.REQUEST_ID_RETURN,Host.URL_ORDER_LIST,params,onBack);
    }

    public static void UploadImageApi(Context context,String file,String usercardfile,String userimagefile,String businessfile,RequestBack onBack){
        /*RequestBody requestBody = new FormBody.Builder()
                .add("imgUrl", imgUrl)
                .add("resourceId", resourceId)
                .build();*/

    }
}
