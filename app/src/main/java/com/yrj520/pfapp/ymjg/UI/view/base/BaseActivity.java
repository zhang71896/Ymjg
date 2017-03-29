package com.yrj520.pfapp.ymjg.UI.view.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.yrj520.pfapp.ymjg.UI.application.SuperApplication;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.progressHUD.KProgressHUD;


/**
 * Title: BaseActivity.class
 * Description: activity基类
 * Copyright: Copyright(c)2017
 * Company: 成都华域天府数据科技有限公司
 * CreateTime: 2017/1/10 11:07
 *
 * @author hc
 * @version 1.0
 */
public class BaseActivity extends FragmentActivity {
    private KProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SuperApplication.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  JPushInterface.onResume(this);
    }

    /**
     * Description:任意页面显示进度框
     *
     * @param text 提示信息
     */
    public void showLoading(String text) {
        onCreateDialog(text);
    }




    /**
     * Description: 全屏变暗的dialog
     *
     * @param text 提示信息
     */
    public void showLoading1(String text) {
        if (progressHUD == null) {
            progressHUD = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(text)
                    .setDimAmount(0.5f);
        }
        progressHUD.show();
    }

    /**
     * Description:关闭进度框
     */
    public void closeLoading() {
        if (progressHUD != null)
            progressHUD.dismiss();
    }

    protected void onCreateDialog(String text) {
        if (progressHUD == null) {
            if (StringUtils.isEmpty(text)) {
                progressHUD = KProgressHUD.create(this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true);
            } else {
                progressHUD = KProgressHUD.create(this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel(text)
                        .setCancellable(true);
            }
        }
        progressHUD.show();
    }

    //处理APP字体跟随系统字体大小改变的问题
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onDestroy() {
        SuperApplication.getInstance().finishActivity(this);
        super.onDestroy();
    }
}
