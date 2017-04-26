package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.AddressAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.AddressData;
import com.yrj520.pfapp.ymjg.UI.event.AddressEvent;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

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
    private RelativeLayout rl_left;
    private TextView tv_add_address;
    private AddressData addressData;
    private AddressAdapter addressAdapter;
    private ListView lv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        EventBus.getDefault().register(this);
        initViews();
        initClickListenner();
        initDatas();
    }

    private void initClickListenner() {
        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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


    public void onEventMainThread(AddressEvent addressEvent){
        String msgType=addressEvent.getmMsg();
        if(msgType.equals(MyConstant.AddAddress)){
            initDatas();
            return;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    private void initDatas(){
        UserApi.AddressManageApi(ActivityAddress.this, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                if(code.equals("200")){
                    ToastUtils.showShort(ActivityAddress.this,"刷新成功");
                    Gson gson=new Gson();
                    addressData=gson.fromJson(response.toString(),AddressData.class);
                    setViews();
                }else{
                    ToastUtils.showShort(ActivityAddress.this,"刷新失败");
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initViews(){
        addressAdapter=new AddressAdapter(ActivityAddress.this);
        tv_center=(TextView)findViewById(R.id.tv_center);
        rl_left=(RelativeLayout) findViewById(R.id.rl_left);
        tv_add_address=(TextView)findViewById(R.id.tv_add_address);
        lv_list=(ListView)findViewById(R.id.lv_list);
        lv_list.setAdapter(addressAdapter);
        tv_center.setText("用户地址管理");
    }

    private void setViews(){
        addressAdapter.addAll(addressData);

    }
}
