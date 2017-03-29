package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.config.AppData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.CasheUtil;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import okhttp3.Request;

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

    private  TextView tv_left;

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
        tv_left=(TextView) findViewById(R.id.tv_left);
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
                        CasheUtil.cleanExternalCache(SettingActivity.this);
                        tv_clear_data.setText("0KB");
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CasheUtil.cleanExternalCache(SettingActivity.this);
                    }
                }).show();


            }
        });

        rl_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,ActivityAboutUs.class);
                startActivity(intent);
            }
        });

        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApi.LogOutApi(SettingActivity.this, new HttpUtil.RequestBack() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        String code=response.optString("code");
                        String message=response.optString("message");
                        ToastUtils.showShort(SettingActivity.this,message);
                        if(code.equals("200")){
                            AppData.getAppData(SettingActivity.this).setTokenValue("");
                            LogUtils.info("tokenValue","tokenValue: "+AppData.getAppData(SettingActivity.this).getTokenValue());
                            Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                            //清空之前栈内的acitivity
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showLoading("提交中...");
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        closeLoading();
                    }
                });
            }
        });
    }
}
