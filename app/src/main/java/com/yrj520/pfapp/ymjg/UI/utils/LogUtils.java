package com.yrj520.pfapp.ymjg.UI.utils;

import android.util.Log;


public class LogUtils {

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 日志标签
    private static final String TAG = "ymjg";
    // 是否打印日志
    public static boolean isDebug = true;

    /**
     * 设置debug 模式
     *
     * @param isDebug true 打印日志 false：不打印
     */
    public static void setiSDebug(boolean isDebug) {
        LogUtils.isDebug = isDebug;
    }

    public static void info(String msg) {
        if (isDebug) {
            Log.i(TAG, msg != null ? msg : "");
        }
    }

    public static void info(String msg, Throwable tr) {
        if (isDebug) {
            Log.i(TAG, msg != null ? msg : "", tr);
        }
    }

    public static void debug(String msg) {
        if (isDebug) {
            Log.d(TAG, msg != null ? msg : "");
        }
    }

    public static void debug(String msg, Throwable tr) {
        if (isDebug) {
            Log.d(TAG, msg != null ? msg : "", tr);
        }
    }

    public static void verbose(String msg) {
        if (isDebug) {
            Log.v(TAG, msg != null ? msg : "");
        }
    }

    public static void verbose(String msg, Throwable tr) {
        if (isDebug) {
            Log.v(TAG, msg != null ? msg : "", tr);
        }
    }

    public static void warn(String msg) {
        if (isDebug) {
            Log.w(TAG, msg != null ? msg : "");
        }
    }

    public static void warn(String msg, Throwable tr) {
        if (isDebug) {
            Log.w(TAG, msg != null ? msg : "", tr);
        }
    }

    public static void error(String msg) {
        if (isDebug) {
            Log.e(TAG, msg != null ? msg : "");
        }
    }

    public static void error(String msg, Throwable tr) {
        if (isDebug) {
            Log.e(TAG, msg != null ? msg : "", tr);
        }
    }

    public static void println(Object obj) {
        if (isDebug) {
            System.out.println(obj);
        }
    }

    public static void debug(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg != null ? msg : "");
        }
    }

    public static void warn(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg != null ? msg : "");
        }
    }

    public static void info(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg != null ? msg : "");
        }
    }

    public static void error(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg != null ? msg : "");
        }
    }

}
