package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.config.AppData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import okhttp3.Request;

/**
 * 登录界面
 * Created by zry on 17/3/28.
 */

public class LoginActivity extends BaseActivity {

    private EditText et_username;

    private EditText et_password;

    private Button btn_login;

    private Button btn_register;

    private Button btn_forgetpsw;

    private  String mUserName;

    private  String mPassWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果本地存在登录令牌
        if(!StringUtils.isEmpty(AppData.getAppData(LoginActivity.this).getTokenValue())){
            String currentToken=AppData.getAppData(LoginActivity.this).getTokenValue();
            String defaultToken=AppData.getAppData(LoginActivity.this).getUserIdToken("3");
            if(currentToken.equals(defaultToken)) {
                Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                //清空之前栈内的acitivity
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
        setContentView(R.layout.login_activity);
        initView();
        iniCLickListener();
    }

    private  void initView(){

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_forgetpsw = (Button)findViewById(R.id.btn_forgetpsw);

    }

    private void iniCLickListener(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });

        btn_forgetpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userForgetPsw();
            }
        });

    }

    private  void userLogin(){

        mUserName=et_username.getText().toString();
        mPassWord=et_password.getText().toString();

        if(StringUtils.isEmpty(mUserName)||!StringUtils.checkMobileNumber(mUserName)){
            ToastUtils.showShort(this,R.string.input_right_mobile_number);
            et_username.requestFocus();
            return;
        }

        if(StringUtils.isEmpty(mPassWord)&&!StringUtils.checkPwd(mPassWord)){
            ToastUtils.showShort(this,R.string.input_right_password);
            et_password.requestFocus();
            return;
        }

            UserApi.LoginApi(this, mUserName, mPassWord, new HttpUtil.RequestBack() {
                @Override
                public void onSuccess(JSONObject response) {
                        String code=response.optString("code");
                        String message=response.optString("message");
                        String auditstate=response.optString("auditstate");

                        ToastUtils.showShort(LoginActivity.this,message);
                        jumpToOtherActivity(code,auditstate);

                }

                @Override
                public void onError(Exception e) {

                }

                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                    showLoading("登录中...");
                }

                @Override
                public void onAfter() {
                    super.onAfter();
                    closeLoading();
                }
            });
    }

    private void jumpToOtherActivity(String code,String auditstate){
        if(code.equals("200")) {
            /**
             * 审核状态(1需审核 2正在审核中 3审核通过)     1跳转到审核界面设置相关的证件照片     2 提示正在审核中     3跳转到首页
             */
            if(auditstate.equals("1")){
                Intent intent = new Intent(LoginActivity.this, VerifyActivity.class);
                startActivity(intent);
                return;
            }
            if(auditstate.equals("2")){
                Intent intent = new Intent(LoginActivity.this, WaitingVerifyActivity.class);
                startActivity(intent);
                return;
            }

            if(auditstate.equals("3")){
                AppData.getAppData(LoginActivity.this).setUserIdToken("3",AppData.getAppData(LoginActivity.this).getTokenValue());
                Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                //清空之前栈内的acitivity
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return;
            }

        }

    }
    private  void userRegister(){
        Intent intent=new Intent(this,RegisterActivity.class);
        intent.putExtra("type",0);
        startActivity(intent);
    }

    private void userForgetPsw(){
        Intent intent=new Intent(this,RegisterActivity.class);
        intent.putExtra("type",1);
        startActivity(intent);

    }
}
