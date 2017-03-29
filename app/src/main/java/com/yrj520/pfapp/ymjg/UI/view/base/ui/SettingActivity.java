package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.utils.CasheUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

/**
 * Title:设置界面
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class SettingActivity extends BaseActivity{
    private RelativeLayout rl_clear_cashe;

    private  RelativeLayout rl_about_us;

    private TextView tv_clear_data;

    private TextView tv_center;

    private Button btn_log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        initViews();
        initClickListenner();
    }

    private void initViews(){
        rl_clear_cashe=(RelativeLayout) findViewById(R.id.rl_clear_cashe);
        rl_about_us=(RelativeLayout) findViewById(R.id.rl_about_us);
        tv_clear_data=(TextView) findViewById(R.id.tv_clear_data);
        btn_log_out=(Button) findViewById(R.id.btn_log_out);
        tv_center=(TextView) findViewById(R.id.tv_center);
        tv_center.setText("设置");
        setDataTv();
    }
    private void setDataTv(){
        try {
            tv_clear_data.setText(CasheUtil.getApplicationCasheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initClickListenner(){

        rl_clear_cashe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog alertDialog =new AlertDialog.Builder(SettingActivity.this).setTitle("你确定要删除缓存?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CasheUtil.cleanInternalCache(SettingActivity.this);
                        setDataTv();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


            }
        });

        rl_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(SettingActivity.this,"关于我们");
            }
        });

        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(SettingActivity.this,"注销");
            }
        });
    }
}
