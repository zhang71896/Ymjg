package com.yrj520.pfapp.ymjg.UI.config;

import android.content.Context;

import com.yrj520.pfapp.ymjg.UI.application.SuperApplication;
import com.yrj520.pfapp.ymjg.UI.utils.SPUtils;


public class AppData {
    private Context context;
    private static final String TOKEN_VALUE = "token_value";
    private static final String USER_ID = "user_id";



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
     * 保存UserId对应的登录成功猴的tokenValue
     */
    public void setUserIdToken(String UserID,String tokenValue) {
        SPUtils.put(context, UserID, tokenValue);
    }

    /**
     * 获取UserIdToken
     */
    public String getUserIdToken(String UserID) {
        return (String) SPUtils.get(context, UserID, "");
    }

    /**
     * 设置UserId
     */
    public void setUserId(String user_id) {
        SPUtils.put(context, USER_ID, USER_ID);
    }

    /**
     * 获取UserId
     */
    public String getUserId() {
        return (String) SPUtils.get(context, USER_ID, "");
    }

}
