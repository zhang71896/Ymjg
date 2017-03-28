package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

/**
 * Created by zry on 17/3/28.
 */

public class RegisterActivity extends BaseActivity {

    private TextView iv_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initViews();
    }

    private void initViews(){
        //type 0:注册用户 1:找回密码
        int type=getIntent().getIntExtra("type",0);
        if(type==0){

        }else{

        }
    }
}
