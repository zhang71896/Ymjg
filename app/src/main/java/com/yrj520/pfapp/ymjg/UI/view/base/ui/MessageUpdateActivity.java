package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

/**
 * Created by zry on 17/4/4.
 */

public class MessageUpdateActivity extends BaseActivity {

    private TextView tv_left;

    private TextView tv_center;

    private Button btn_save;

    private EditText et_message;

    private String shopName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopName= getIntent().getStringExtra("shopName");
        setContentView(R.layout.activity_update_message);
        initViews();
        initClickListenner();
        setViews();
    }

    private void initClickListenner() {
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {
    }

    private void setViews() {
        tv_center.setText("店铺名称修改");
        et_message.setText(shopName);
    }

    private void initViews() {
        tv_left=(TextView) findViewById(R.id.tv_left);
        tv_center=(TextView) findViewById(R.id.tv_center);
        btn_save=(Button)findViewById(R.id.btn_save);
        et_message=(EditText) findViewById(R.id.et_message);

    }
}
