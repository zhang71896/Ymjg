package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by zry on 17/4/4.
 */

public class ActivityFeedBack extends BaseActivity {
    private TextView tv_left;

    private TextView tv_center;

    private Button btn_submit;

    private EditText et_feedback;

    private EditText et_mobile;

    private String mPhone;

    private String mFeedBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvitiy_feed_back);
        initViews();
        initClickListenner();
        setViews();
    }

    private void setViews() {
        tv_center.setText("意见反馈");
    }

    private void initClickListenner(){
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedBackSubmit();
            }
        });
    }

    private void feedBackSubmit() {
        mPhone=et_mobile.getText().toString();
        mFeedBack=et_feedback.getText().toString();
        if(inputCheck()){
            UserApi.FeedBackApi(ActivityFeedBack.this, mFeedBack, mPhone, new HttpUtil.RequestBack() {
                @Override
                public void onSuccess(JSONObject response) {
                    String code=response.optString("code");
                    String meg=response.optString("meg");
                    ToastUtils.showShort(ActivityFeedBack.this,meg);
                    if(code.equals("200")){
                      finish();
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
    }

    private boolean inputCheck(){
        boolean ispass=true;
        if(StringUtils.isEmpty(mPhone)||!StringUtils.checkMobileNumber(mPhone)){
            ToastUtils.showShort(this,R.string.input_right_mobile_number);
            et_mobile.requestFocus();
            return false;
        }

        if(StringUtils.isEmpty(mFeedBack)){
            ToastUtils.showShort(this,R.string.please_input_messagecode);
            et_feedback.requestFocus();
            return false;
        }
        return ispass;
    }

    private void initViews() {
        tv_left=(TextView)findViewById(R.id.tv_left);
        tv_center=(TextView)findViewById(R.id.tv_center);
        btn_submit=(Button) findViewById(R.id.btn_submit);
        et_feedback=(EditText) findViewById(R.id.et_feedback);
        et_mobile=(EditText) findViewById(R.id.et_mobile);

    }
}
