package com.yrj520.pfapp.ymjg.UI.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.jock.pickerview.dao.DBManager;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;

import java.util.Stack;

/**
 * //  ┏┓　　　┏┓
 * //┏┛┻━━━┛┻┓
 * //┃　　　　　　　┃
 * //┃　　　━　　　┃
 * //┃　┳┛　┗┳　┃
 * //┃　　　　　　　┃
 * //┃　　　┻　　　┃
 * //┃　　　　　　　┃
 * //┗━┓　　　┏━┛
 * //    ┃　　　┃   神兽保佑
 * //    ┃　　　┃   代码无BUG！
 * //    ┃　　　┗━━━┓
 * //    ┃　　　　　　　┣┓
 * //    ┃　　　　　　　┏┛
 * //    ┗┓┓┏━┳┓┏┛
 * //      ┃┫┫　┃┫┫
 * //      ┗┻┛　┗┻┛
 */


public class SuperApplication extends Application {
    private static Stack<Activity> activityStack;
    private static SuperApplication singleton;
    public static boolean isUnity = false;//如果在unity环境下打包则为true，否则为false
    public static Context context;
    private DBManager dbHelper;


    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        context=this.getApplicationContext();
        singleton = this;
        LogUtils.isDebug = true;
    }




    public static SuperApplication getInstance() {
        return singleton;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }


    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }

}
