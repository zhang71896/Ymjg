package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

/**
 * Title:网页访问界面
 * Description:0代表协议 1代表支付界面
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class WebViewActivity extends BaseActivity {

    private WebView webView;

    private TextView tv_left;

    private TextView tv_center;

    /**
     * 0 代表跳转到协议 1代表跳转到支付界面
     */
    private int mType=0;

    private String address_id;

    private String order_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aggrenment_activity);
        webView=(WebView) findViewById(R.id.webView);
        tv_left=(TextView) findViewById(R.id.tv_left);
        tv_center=(TextView) findViewById(R.id.tv_center);
        tv_center.setText("用户协议");
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mType=getIntent().getIntExtra("type",0);
        address_id=getIntent().getStringExtra("address_id");
        order_id=getIntent().getStringExtra("order_id");

        if(mType==0) {
            webView.loadUrl("file:///android_asset/guestsAgreement.html");
        }else{
            UserApi.ProduceOrderAlipay(WebViewActivity.this,address_id,order_id,new HttpUtil.RequestBack(){

                @Override
                public void onSuccess(JSONObject response) {

                    LogUtils.info("aliPay","address_id"+address_id+"order_id"+order_id);

                }

                @Override
                public void onError(Exception e) {
                    LogUtils.info("aliPay","address_id"+address_id+"order_id"+order_id);

                }
            });
            //webView.loadUrl(Host.URL_ORDER_ALIPAY);
        }
    }
}
