package com.yrj520.pfapp.ymjg.UI.api;

import android.content.Context;
import com.yrj520.pfapp.ymjg.UI.config.Constant;
import com.yrj520.pfapp.ymjg.UI.net.Host;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil.RequestBack;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
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
     * @param context
     * @param phone
     * @param password
     * @param onBack
     */
    public static void LoginApi(Context context,String phone,String password, RequestBack onBack){
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", String.valueOf(password));
        HttpUtil.doGet(context, Constant.REQUEST_ID_RETURN, Host.URL_LOGIN, params, onBack);
    }
}
