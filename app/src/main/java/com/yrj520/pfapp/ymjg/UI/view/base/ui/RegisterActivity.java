package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import okhttp3.Request;


/**
 * 注册和找回密码界面
 *
 * Created by zry on 17/3/28.
 */

public class RegisterActivity extends BaseActivity {

    private TextView tv_left;

    private TextView tv_center;

    private EditText et_mobile;

    private  EditText et_message_code;

    private  EditText et_psw1;

    private  EditText et_psw2;

    private Button btn_get_message_code;

    private Button btn_register;

    private  Button btn_aggrement;

    private RelativeLayout rl_aggrement;

    private RelativeLayout rl_ensure_psw;

    private  TextView tv_line1;

    private  TextView tv_line2;

    private String mPhone="";

    private String psw1="";

    private String psw2="";

    private String messageCode="";

    private TimeCount timeCount;

    //type 1:注册用户 2:找回密码
    private String mScene;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initViews();
        initClickListenner();
    }

    private void initClickListenner() {

        btn_get_message_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getMessageCode();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        btn_aggrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });

        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews(){

        tv_left= (TextView) findViewById(R.id.tv_left);

        tv_center=(TextView)findViewById(R.id.tv_center);

        et_mobile=(EditText) findViewById(R.id.et_mobile);

        et_message_code=(EditText) findViewById(R.id.et_message_code);

        et_psw1=(EditText) findViewById(R.id.et_psw1);

        et_psw2=(EditText) findViewById(R.id.et_psw2);

        btn_get_message_code=(Button)findViewById(R.id.btn_get_message_code);

        btn_register=(Button)findViewById(R.id.btn_register);

        btn_aggrement=(Button)findViewById(R.id.btn_aggrement);

        rl_aggrement=(RelativeLayout)findViewById(R.id.rl_aggrement);

        rl_ensure_psw=(RelativeLayout)findViewById(R.id.rl_ensure_psw);

        tv_line1=(TextView) findViewById(R.id.tv_line1);

        tv_line2=(TextView) findViewById(R.id.tv_line2);

        tv_center.setText("注册");

         int mType=getIntent().getIntExtra("type",0);
         if(mType==0){
             mScene="1";
         }else {
             mScene="2";
         }

        if(mScene.equals("2")){
            tv_center.setText("找回密码");
            btn_register.setText("找回");
            rl_aggrement.setVisibility(View.GONE);
            rl_ensure_psw.setVisibility(View.GONE);
            tv_line1.setVisibility(View.GONE);
            tv_line2.setVisibility(View.GONE);
        }
    }

    private void getMessageCode(){
        mPhone=et_mobile.getText().toString();
        if(StringUtils.isEmpty(mPhone)||!StringUtils.checkMobileNumber(mPhone)){
            ToastUtils.showShort(this,R.string.input_right_mobile_number);
            et_mobile.requestFocus();
            return;
        }

        UserApi.SendMessageApi(this,mPhone,mScene, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String message= response.optString("message");
                if(code.equals("200")){
                    ToastUtils.showShort(RegisterActivity.this, "短信验证码发送成功");
                }
                timeCount = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                timeCount.start();// 开始计时
            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                showLoading("发送中...");

            }

            @Override
            public void onAfter() {
                super.onAfter();
                closeLoading();
            }
        });
    }

    private void registerUser(){
            if(checkSubmitDataCorrect()){
                if(mScene.equals("1")){
                UserApi.Register(RegisterActivity.this,mPhone,psw1,messageCode, new HttpUtil.RequestBack() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        String code=response.optString("code");
                        String message=response.optString("message");
                        ToastUtils.showShort(RegisterActivity.this,message);
                        if(code.equals("200")){
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
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
                        showLoading("注册中...");
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        closeLoading();
                    }
                });//找回密码
            }else if(mScene.equals("2")){
                    UserApi.FindPasswordApi(RegisterActivity.this, mPhone, psw1, messageCode, new HttpUtil.RequestBack() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            String code=response.optString("code");
                            String meg=response.optString("meg");
                            ToastUtils.showShort(RegisterActivity.this,meg);
                            if(code.equals("200")){
                                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                //清空之前activity的栈
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
                            showLoading("找回中...");
                        }

                        @Override
                        public void onAfter() {
                            super.onAfter();
                            closeLoading();
                        }
                    }
                    );
                }
            }
    }

    //判断提交数据是否正确
    private boolean checkSubmitDataCorrect(){
        boolean ispass=true;
        mPhone=et_mobile.getText().toString();
        psw1=et_psw1.getText().toString();
        messageCode=et_message_code.getText().toString();

        if(StringUtils.isEmpty(mPhone)||!StringUtils.checkMobileNumber(mPhone)){
            ToastUtils.showShort(this,R.string.input_right_mobile_number);
            et_mobile.requestFocus();
            return false;
        }

        if(StringUtils.isEmpty(messageCode)){
            ToastUtils.showShort(this,R.string.please_input_messagecode);
            et_message_code.requestFocus();
            return false;
        }

        if(StringUtils.isEmpty(psw1)||!StringUtils.checkPwd(psw1)){
            ToastUtils.showShort(this,R.string.input_right_password);
            et_psw1.requestFocus();
            return false;
        }
        //注册场景
        if(mScene.equals("1")) {
            psw2 = et_psw2.getText().toString();
            if (StringUtils.isEmpty(psw2) || !StringUtils.checkPwd(psw2)) {
                ToastUtils.showShort(this, R.string.input_right_password);
                et_psw2.requestFocus();
                return false;
            }

            if (!psw1.equals(psw2)) {
                ToastUtils.showShort(this, R.string.input_same_password);
                et_psw1.requestFocus();
                return false;
            }
        }
        return ispass;
    }

    //倒计时计数
    class TimeCount extends CountDownTimer {
        private TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {// 计时完毕时触发
            btn_get_message_code.setText("获取验证码");
            btn_get_message_code.setClickable(true);
        }
        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            btn_get_message_code.setClickable(false);
            btn_get_message_code.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
