package com.yrj520.pfapp.ymjg.UI.config;

import android.content.Context;

import com.yrj520.pfapp.ymjg.UI.application.SuperApplication;
import com.yrj520.pfapp.ymjg.UI.utils.SPUtils;


public class AppData {
    private Context context;
    private static final String TOKEN_VALUE = "token_value";
    private static final String USER_ID = "user_id";
    private static final String IS_FIRST_OPEN = "is_first_open";
    private static final String USER_ACCOUNT = "user_account";

    private static final String USER_HEAD_PIC = "user_headPic";
    private static final String USER_PHONE = "user_phone";
    private static final String USER_NICKNAME = "user_nickName";
    private static final String USER_GENDER = "user_gender";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_SIGN = "user_sign";
    private static final String USER_QQ = "user_qq";
    private static final String USER_SINA = "user_sina";
    private static final String USER_WECHAT = "user_wechat";
    private static final String USER_CODE = "user_code";
    private static final String USER_ACTIVE_DEGREE = "user_active_degree ";
    private static final String PAY_STATUS = "pay_status";


    private AppData() {

        this.context = SuperApplication.getInstance().getApplicationContext();
    }

    public static AppData getAppData(Context context) {
        return new AppData();
    }

    /**
     * 保存token
     */
    public void setTokenValue(String tokenValue) {
        SPUtils.put(context, TOKEN_VALUE, tokenValue);
    }

    /**
     * 获取token
     */
    public String getTokenValue() {
        return (String) SPUtils.get(context, TOKEN_VALUE, "");
    }

    /**
     * 保存用户Id
     *//*
    public void setUserId(Long userId) {
        SPUtils.put(context, USER_ID, userId);
    }

    *//**
     * 获取用户id
     *//*
    public Long getUserId() {
        return (Long) SPUtils.get(context, USER_ID, -1);
    }

    *//**
     * 保存用户账号
     *//*
    public void setUserAccount(String userName) {
        SPUtils.put(context, USER_ACCOUNT, userName);
    }

    *//**
     * 获取用户账号
     *//*
    public String getUserAccount() {
        return (String) SPUtils.get(context, USER_ACCOUNT, "");
    }

    *//**
     * 保存用户账号
     *//*
    public void setUserPhone(String phone) {
        SPUtils.put(context, USER_PHONE, phone);
    }

    *//**
     * 获取用户账号
     *//*
    public String getUserPhone() {
        return (String) SPUtils.get(context, USER_PHONE, "");
    }

    *//**
     * 设置是否第一次打开
     *//*
    public void setIsFirstOpen(Boolean isFirst) {
        SPUtils.put(context, IS_FIRST_OPEN, isFirst);
    }

    *//**
     * 是否第一次打开
     *//*
    public Boolean isFirstLogin() {
        return (Boolean) SPUtils.get(context, IS_FIRST_OPEN, true);
    }


    *//**
     * 保存头像
     *//*
    public void setHeadPic(String headPic) {
        SPUtils.put(context, USER_HEAD_PIC, headPic);
    }

    *//**
     * 获取头像地址sd
     *//*
    public String getHeadPic() {
        return (String) SPUtils.get(context, USER_HEAD_PIC, "");
    }

    *//**
     * 保存昵称
     *//*
    public void setNickName(String nickName) {
        SPUtils.put(context, USER_NICKNAME, nickName);
    }

    *//**
     * 获取昵称
     *//*
    public String getNickName() {
        return (String) SPUtils.get(context, USER_NICKNAME, "");
    }


    *//**
     * 保存性别
     *//*
    public void setGender(int gender) {
        SPUtils.put(context, USER_GENDER, gender);
    }

    *//**
     * 获取性别标记
     *//*
    public Integer getGender() {
        return (Integer) SPUtils.get(context, USER_GENDER, 0);
    }

    *//**
     * 保存邮箱
     *//*
    public void setEmail(String email) {
        SPUtils.put(context, USER_EMAIL, email);
    }

    *//**
     * 获取邮箱
     *//*
    public String getEmail() {
        return (String) SPUtils.get(context, USER_EMAIL, "");
    }

    *//**
     * 保存签名
     *//*
    public void setSign(String sign) {
        SPUtils.put(context, USER_SIGN, sign);
    }

    *//**
     * 获取签名
     *//*
    public String getSign() {
        return (String) SPUtils.get(context, USER_SIGN, "");
    }

    *//**
     * 保存用户激活码
     *//*
    public void setUserCode(String userCode) {
        SPUtils.put(context, USER_CODE, userCode);
    }

    *//**
     * 获取用户激活码
     *//*
    public String getUserCode() {
        return (String) SPUtils.get(context, USER_CODE, "");
    }

    *//**
     * 保存用户活跃度
     *//*
    public void setUserActiveDegree(Long ActiveDegree) {
        SPUtils.put(context, USER_ACTIVE_DEGREE, ActiveDegree);
    }

    *//**
     * 获取用户活跃度
     *//*
    public Long getUserActiveDegree() {
        return (Long) SPUtils.get(context, USER_ACTIVE_DEGREE, 0);
    }

    *//**
     * 保存付费状态,0,未付费，1付费
     *//*
    public void setPayStatus(Integer payStatus) {
        SPUtils.put(context, PAY_STATUS, payStatus);
    }


    *//**
     * 获取付费状态,0：未付费，1：付费
     *//*
    public Integer getPayStatus() {
        return (Integer) SPUtils.get(context, PAY_STATUS, 0);
    }


    *//**
     * 保存是否绑定QQ
     *//*
    public void setUserQq(String qq) {
        SPUtils.put(context, USER_QQ, qq);
    }

    *//**
     * 获取是否绑定QQ
     *//*
    public String getUserQq() {
        return (String) SPUtils.get(context, USER_QQ, "");
    }


    *//**
     * 保存是否绑定微信
     *//*
    public void setUserWechat(String wechat) {
        SPUtils.put(context, USER_WECHAT, wechat);
    }

    *//**
     * 获取是否绑定微信
     *//*
    public String getUserWechat() {
        return (String) SPUtils.get(context, USER_WECHAT, "");
    }


    *//**
     * 保存是否绑定新浪微博
     *//*
    public void setUserSina(String sina) {
        SPUtils.put(context, USER_SINA, sina);
    }

    *//**
     * 获取是否绑定新浪微博
     *//*
    public String getUserSina() {
        return (String) SPUtils.get(context, USER_SINA, "");
    }

    *//**
     * 判断是否登录
     *//*
    public boolean isLogin() {
        if (StringUtils.isEmpty(getUserPhone()) && getUserId() < 0) {
            return false;
        }
        return true;
    }

    //注销
    public void logout() {
        SPUtils.clear(context);
    }
*/
}
