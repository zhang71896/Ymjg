package com.yrj520.pfapp.ymjg.UI.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.entity.DefaultAddressData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.ActivityAddress;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.WebViewActivity;

import org.json.JSONObject;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * 支付相关的信息选择对话框
 * @author Rock
 * @version 1.0
 */

public class PayMessageDialog extends Dialog{
    private Context mContext;
    private TextView tv_close;
    private TextView tv_phone;
    private TextView tv_lianxiren;
    private DefaultAddressData defaultAddressData=null;
    private TextView tv_address;
    private  TextView tv_pay_value;
    private RelativeLayout rl_address;
    private Button btn_pay;
    private String mAddress_id;
    private String mOrder_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay_choice);
        tv_close=(TextView) findViewById(R.id.tv_close);
        tv_phone=(TextView) findViewById(R.id.tv_phone);
        tv_lianxiren=(TextView)findViewById(R.id.tv_lianxiren);
        tv_address=(TextView)findViewById(R.id.tv_address);
        tv_pay_value=(TextView)findViewById(R.id.tv_pay_value);
        btn_pay=(Button)findViewById(R.id.btn_pay);
        rl_address=(RelativeLayout)findViewById(R.id.rl_address);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        initClickListenner();

    }

    public PayMessageDialog(Context context,String order_id) {
        super(context);
        mContext=context;
        mOrder_id=order_id;
    }


    private void initClickListenner() {
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, WebViewActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("address_id",mAddress_id);
                intent.putExtra("order_id",mOrder_id);
                mContext.startActivity(intent);
            }
        });

        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rl_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext, ActivityAddress.class);
                mContext.startActivity(intent);
                dismiss();
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void initDatas(final String totalPrices) {
        UserApi.GetUserDefaultAddressApi(mContext, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                JSONObject data=response.optJSONObject("data");
                if(code.equals("200")){
                    Gson gson=new Gson();
                    if(data!=null) {
                        defaultAddressData = gson.fromJson(response.toString(), DefaultAddressData.class);
                    }
                    if(defaultAddressData!=null)
                       setViews(totalPrices);
                    else{
                        Intent intent =new Intent(mContext, ActivityAddress.class);
                        mContext.startActivity(intent);
                        dismiss();
                    }
                    }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void setViews(String totalPrices){
        mAddress_id=defaultAddressData.getData().getAddress_id();
        if(!StringUtils.isEmpty(defaultAddressData.getData().getSh_phone())) {
            tv_phone.setText(defaultAddressData.getData().getSh_phone());
        }
        if(!StringUtils.isEmpty(defaultAddressData.getData().getConsignee())) {
            tv_lianxiren.setText(defaultAddressData.getData().getConsignee());
        }
        if(!StringUtils.isEmpty(defaultAddressData.getData().getProvicename())) {
            tv_address.setText(defaultAddressData.getData().getProvicename() + defaultAddressData.getData().getCityname() + defaultAddressData.getData().getDistrictname() + defaultAddressData.getData().getSh_address());
        }
           tv_pay_value.setText("¥ "+totalPrices);
    }

}
