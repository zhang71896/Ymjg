package com.yrj520.pfapp.ymjg.UI.api;

import android.content.Context;

import com.yrj520.pfapp.ymjg.UI.config.Constant;
import com.yrj520.pfapp.ymjg.UI.entity.ConsigneeData;
import com.yrj520.pfapp.ymjg.UI.entity.PersonMessageData;
import com.yrj520.pfapp.ymjg.UI.entity.RequestPersonMessageData;
import com.yrj520.pfapp.ymjg.UI.net.Host;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil.RequestBack;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

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
        HttpUtil.doPost(context, Constant.REQUEST_ID_RETURN, Host.URL_LOGIN, params, onBack);
    }

    /**
     * 退出登录接口
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void LogOutApi(Context context,RequestBack onBack){
        HttpUtil.doPost(context, Constant.REQUEST_ID_RETURN, Host.URL_LOGOUT, null, onBack);
    }

    /**
     * 访问主页接口
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void IndexApi(Context context,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        HttpUtil.doPost(context, Constant.REQUEST_ID_RETURN, Host.URL_INDEX, params, onBack);
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
        HttpUtil.doPost(context, Constant.REQUEST_ID_RETURN, Host.URL_SENDMESSAGE, params, onBack);
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
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_REGISTER,params,onBack);
    }

    /**
     * 直接获取1,2级分类
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void Get12GoodsDirectlyApi(Context context,RequestBack onBack){
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_GET_12GOODS_DIRECTLY,null,onBack);
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
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_GET_12GOODS_SINGLELY,params,onBack);
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
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_GET_3GOODS,params,onBack);
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
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_GET_GOODS_SPEC,params,onBack);
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
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_OPERATE_GOODS_NUM,params,onBack);
    }

    /**
     * 生成订单
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void ProduceOrderApi(Context context,RequestBack onBack){
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_PRODUCE_ORDER,null,onBack);
    }

    /**
     * 购物车总价总数量
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void ShoppingCatApi(Context context,RequestBack onBack){
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_SHOPPING_CAT,null,onBack);
    }

    /**
     * 清空购物车
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void ClearShoppingCatApi(Context context,RequestBack onBack){
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_CLEAR_SHOPPING_CAT,null,onBack);
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
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_ORDER_LIST,params,onBack);
    }

    /**
     * 上传图像
     * @param context 上下文对象
     * @param fileName 上传头像（file）	上传身份证（正面usercardfile，反面userimagefile）	上传营业执照（businessfile）
     * @param file file路径参数
     * @param onBack 回调函数 code=200	meg=“成功” code=400 meg=“失败”
     */
    public static void UploadImageApi(Context context,String fileName ,String file, RequestBack onBack){
        RequestBody requestBody = new FormBody.Builder()
                .add(fileName, file)
                .build();
        LogUtils.info("put params:" + " file: " + file );
        HttpUtil.doPut(context, Constant.REQUEST_ID_RETURN, requestBody, Host.URL_UPLOAD_IMAGE, onBack);
    }

    public static void uploadResApi(Context context, File file, RequestBack onBack) {
        Map<String, String> params = new HashMap<>();
        HttpUtil.doPostFile(context, Constant.REQUEST_ID_RETURN, Host.URL_UPLOAD_IMAGE, params, file, onBack);
    }

    /**
     *查询个人资料
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void QueryPersonalMessageApi(Context context, RequestBack onBack) {

        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_QUERY_USERMESSAGE,null,onBack);
    }

    /**
     * 修改个人信息
     * @param context 上下文对象
     * @param personMessageData 个人信息实体
     * @param onBack 回调函数
     */
    public static void UpdatePersonalMessageApi(Context context, PersonMessageData personMessageData, RequestBack onBack) {
        Map<String, String> params = new HashMap<>();
        params.put(personMessageData.keyaddress,personMessageData.getAddress());
        params.put(personMessageData.Keydistrict,personMessageData.getDistrict());
        params.put(personMessageData.Keylianxiren,personMessageData.getAddress());
        params.put(personMessageData.Keyprovice,personMessageData.getProvice());
        params.put(personMessageData.Keyuserimg,personMessageData.getUserimg());
        params.put(personMessageData.Keycity,personMessageData.getCity());
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_UPDATE_USERINFO,params,onBack);
    }

    /**
     * 修改审核信息
     * @param context 上下文对象
     * @param rPMD 审核信息实体
     * @param onBack 回调函数
     */
    public static void UpdateRequestPersonalMessageApi(Context context, RequestPersonMessageData rPMD, RequestBack onBack) {
        Map<String, String> params = new HashMap<>();
        params.put(rPMD.Keybusinessurl,rPMD.getBusinessurl());
        params.put(rPMD.Keyidcard,rPMD.getIdcard());
        params.put(rPMD.Keyidcardurl,rPMD.getIdcardurl());
        params.put(rPMD.Keyusercardurl,rPMD.getUsercardurl());
        params.put(rPMD.Keyusername,rPMD.getUsername());
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_UPDATE_USERINFO,params,onBack);
    }

    /**
     * 找回密码
     * @param context 上下文对象
     * @param phone 手机号
     * @param password 密码
     * @param captcha 短息验证码
     * @param onBack 回调函数
     */
    public static void FindPasswordApi(Context context,String phone,String password,String captcha,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("phone",phone);
        params.put("password",password);
        params.put("captcha",captcha);
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_FIND_PASSWORD,params,onBack);
    }

    /**
     * 地址管理
     * @param context 上下文对象
     * @param onBack 回调函数
     */
    public static void AddressManageApi(Context context,RequestBack onBack){
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_ADDRESS_MANAGE,null,onBack);
    }

    /**
     * 修改或添加收货人地址 新增addressId="" 修改addressId="传入值"
     * @param context 上下文对象
     * @param consigneeData 收货人数据
     * @param onBack 回调函数
     */
    public  static void UpdateAddressApi(Context context, ConsigneeData consigneeData, RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put(consigneeData.Keyarea_id,consigneeData.getArea_id());
        params.put(consigneeData.Keycity,consigneeData.getCity());
        params.put(consigneeData.Keyconsignee,consigneeData.getConsignee());
        params.put(consigneeData.KeyisDefault,consigneeData.getIsDefault());
        params.put(consigneeData.Keyprovice,consigneeData.getProvice());
        params.put(consigneeData.Keysh_address,consigneeData.getSh_address());
        params.put(consigneeData.Keysh_phone,consigneeData.getSh_phone());
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_UPDATE_ADDRESS,null,onBack);
    }

    /**
     * 设置默认地址
     * @param context 上下文对象
     * @param address_id 地址 id
     * @param onBack 回调函数
     */
    public static void SetDefaultAddressApi(Context context,String address_id,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("address_id",address_id);
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_SET_DEFAULT_ADDRESS,null,onBack);
    }

    /**
     *查询用户默认地址
     * @param context 上下文对象
     * @param onBack
     */
    public static void GetUserDefaultAddressApi(Context context,RequestBack onBack){
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_QUERY_DEFAULT_ADDRESS,null,onBack);

    }

    /**
     * 删除地址
     * @param context 上下文对象
     * @param address_id 地址 id
     * @param onBack 回调函数
     */
    public static void DeleteAddressApi(Context context,String address_id,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("address_id",address_id);
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_DELETE_DEFAULT_ADDRESS,null,onBack);
    }

    /**
     * 意见反馈
     * @param context 上下文对象
     * @param feedcontent 地址 id
     * @param phone 手机号码
     * @param onBack 回调函数
     */
    public static void FeedBackApi(Context context,String feedcontent,String phone,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("feedcontent",feedcontent);
        params.put("phone",phone);
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_FEEDBACKS,null,onBack);
    }

    /**
     * 改变订单状态
     * 请求参数： order_id：订单id	order_status:订单状态（0待发货，2已发货，3已取消  4已完成 5进行删除操作）
     * @param context 上下文对象
     * @param order_id 订单id
     * @param order_status 订单状态
     * @param onBack 回调函数
     */
    public static void ChangeOrderStatus(Context context,String order_id,String order_status,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("order_id",order_id);
        params.put("order_status",order_status);
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_UPDATE_ORDER_STATUS,null,onBack);
    }

    /**
     * 生成订单信息并构造支付请求
     * @param context 上下文对象
     * @param order_id 订单id
     * @param address_id 地址id
     * @param onBack 回调函数
     */
    public static void ProduceOrderAlipay(Context context,String order_id,String address_id,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("order_id",order_id);
        params.put("address_id",address_id);
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_ORDER_ALIPAY,null,onBack);

    }

    /**
     * 付款完成后接口
     * @param context 上下文对象
     * @param order_id 订单id
     * @param code 地址id
     * @param onBack 回调函数
     */
    public static void SelectOrderAlipay(Context context,String order_id,String code,RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("order_id",order_id);
        params.put("code",code);
        HttpUtil.doPost(context,Constant.REQUEST_ID_RETURN,Host.URL_ORDER_AFTERPAY,null,onBack);
    }
}
