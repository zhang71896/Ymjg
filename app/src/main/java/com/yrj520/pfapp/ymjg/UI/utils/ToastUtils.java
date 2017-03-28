package com.yrj520.pfapp.ymjg.UI.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Title: ToastUtils.java
 * Description: Toast统一管理类
 * Copyright:Copyright(c)2016
 * Company: 成都华域天府数据科技有限公司
 * CreateTime:2016/3/7  15:35
 *
 * @author hc
 * @version 1.0
 */
public class ToastUtils {

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast toast = null;

    public static void showShort(Context context, String hint) {
        if (toast == null) {
            toast = Toast.makeText(context, hint, Toast.LENGTH_SHORT);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0,80);
        toast.show();
    }

    public static void showShort(Context context, int retId) {
        if (toast == null) {
            toast = Toast.makeText(context, retId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(retId);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 80);
        toast.show();
    }

    public static void showLong(Context context, String hint) {
        if (toast == null) {
            toast = Toast.makeText(context, hint, Toast.LENGTH_LONG);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.setGravity(Gravity.CENTER, 0, 80);
        toast.show();
    }


}
