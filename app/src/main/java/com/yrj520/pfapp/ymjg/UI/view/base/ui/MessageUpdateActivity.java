package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.PersonMessageData;
import com.yrj520.pfapp.ymjg.UI.event.PersonalMessagEvent;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by zry on 17/4/4.
 */

public class MessageUpdateActivity extends BaseActivity {

    private RelativeLayout rl_left;

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
        rl_left.setOnClickListener(new View.OnClickListener() {
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
        PersonMessageData personMessageData=new PersonMessageData();
        personMessageData.setLianxiren(et_message.getText().toString());
        UserApi.UpdatePersonalMessageApi(MessageUpdateActivity.this, personMessageData, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(MessageUpdateActivity.this,meg);
                if(code.equals("200")) {
                    finish();
                    PersonalMessagEvent personalMessagEvent=new PersonalMessagEvent(MyConstant.UpdatePersonalMessage);
                    EventBus.getDefault().post(personalMessagEvent);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void setViews() {
        tv_center.setText("店铺名称修改");
        et_message.setText(shopName);
    }

    private void initViews() {
        rl_left=(RelativeLayout) findViewById(R.id.rl_left);
        tv_center=(TextView) findViewById(R.id.tv_center);
        btn_save=(Button)findViewById(R.id.btn_save);
        et_message=(EditText) findViewById(R.id.et_message);

    }
}
