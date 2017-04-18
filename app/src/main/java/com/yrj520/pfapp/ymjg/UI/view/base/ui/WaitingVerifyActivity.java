package com.yrj520.pfapp.ymjg.UI.view.base.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

/**
 * Title:等待验证界面
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class WaitingVerifyActivity extends BaseActivity {

    private TextView tv_left;

    private TextView tv_center;

    private Button btn_back_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_verify_activity);
        tv_left=(TextView)findViewById(R.id.tv_left);
        tv_center=(TextView)findViewById(R.id.tv_center);
        btn_back_login=(Button)findViewById(R.id.btn_back_login);

        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitingVerifyActivity.this,LoginActivity.class);
                //清空之前activity的栈
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
