package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class ActivityAddress extends BaseActivity {
    private TextView tv_center;
    private TextView tv_left;
    private TextView tv_add_address;
    private ListView lv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initViews();
        initClickListenner();
        initDatas();
    }

    private void initClickListenner() {
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityAddress.this,ActivityAddAddress.class);
                startActivity(intent);
            }
        });

    }

    private void initDatas(){
        UserApi.AddressManageApi(ActivityAddress.this, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                LogUtils.info("AddressManageApi :",response.toString());
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(ActivityAddress.this,meg);
                if(code.equals("200")){

                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initViews(){
        tv_center=(TextView)findViewById(R.id.tv_center);
        tv_left=(TextView)findViewById(R.id.tv_left);
        tv_add_address=(TextView)findViewById(R.id.tv_add_address);
        lv_list=(ListView)findViewById(R.id.lv_list);
        tv_center.setText("用户地址管理");
    }
}
