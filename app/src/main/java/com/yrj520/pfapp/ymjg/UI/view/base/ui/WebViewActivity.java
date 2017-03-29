package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
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

    private RelativeLayout rl_left;

    private TextView tv_center;

    private RelativeLayout headr_bar;

    private String orderInfo;

    /**
     * 0 代表跳转到协议 1代表跳转到支付界面
     */
    private int mType = 0;

    private String address_id;

    private String order_id;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String code=msg.obj.toString();

                if(code.equals("9000")){
                    ToastUtils.showShort(WebViewActivity.this,"支付成功!");
                    changeOrder(code);
                    return;

                }else if(code.equals("8000")){
                    ToastUtils.showShort(WebViewActivity.this,"正在处理中!");
                    finish();
                    return;
                }else{
                    ToastUtils.showShort(WebViewActivity.this,"支付失败!");
                    finish();
                    return;
                }
            }
        }

    };

    private  void changeOrder(String code){
        UserApi.SelectOrderAlipay(WebViewActivity.this, order_id, code, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(WebViewActivity.this,meg);
                if(code.equals("200")){
                    Intent intent=new Intent(WebViewActivity.this,OrderCooperateActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aggrenment_activity);
        webView = (WebView) findViewById(R.id.webView);
        rl_left = (RelativeLayout) findViewById(R.id.rl_left);
        tv_center = (TextView) findViewById(R.id.tv_center);
        headr_bar = (RelativeLayout) findViewById(R.id.headr_bar);
        tv_center.setText("用户协议");
        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mType = getIntent().getIntExtra("type", 0);
        address_id = getIntent().getStringExtra("address_id");
        order_id = getIntent().getStringExtra("order_id");
        if (mType == 0) {
            webView.loadUrl("file:///android_asset/guestsAgreement.html");
        } else {
            headr_bar.setVisibility(View.GONE);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            UserApi.ProduceOrderAlipay(WebViewActivity.this, order_id, address_id, new HttpUtil.StringRequestBack() {
                @Override
                public void onSuccess(String response) {
                    // Enable Javascript

                    // Force links and redirects to open in the WebView instead of in a browse
                    //webView.addJavascriptInterface(new InJavaScriptLocalObj(),"local_obj");
                    webView.loadDataWithBaseURL(null, response.toString(),
                            "text/html", "utf-8", null);
                    webView.setWebViewClient(new MyWebViewClient());
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
    }

    final class MyWebViewClient extends WebViewClient {
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            LogUtils.info("WebResourceResponse",url);

            return super.shouldInterceptRequest(view, url);
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
/*
            LogUtils.info("onPageStarted","onPageStarted");
*/
            super.onPageStarted(view, url, favicon);
        }

        public void onPageFinished(WebView view, String url) {
            final PayTask task = new PayTask(WebViewActivity.this);
            final String ex = task.fetchOrderInfoFromH5PayUrl(url);
            LogUtils.info("WebResourceResponse ex:","here"+ex);
            if (!TextUtils.isEmpty(ex)) {
                //调用支付接口进行支付
                new Thread(new Runnable() {
                    public void run() {
                        H5PayResultModel result = task.h5Pay(ex, true);
                        //处理返回结果
                        LogUtils.info("H5PayResultModel",result.getResultCode().toString());
                        if(!StringUtils.isEmpty(result.getResultCode())){
                            String code=result.getResultCode();
                            if(code.equals("9000")){
                                Message message=mHandler.obtainMessage();
                                message.what=1;
                                message.obj=code;
                                mHandler.sendMessage(message);
                            }
                        }
                    }
                }).start();
            } else {

            }
            super.onPageFinished(view, url);

        }


        final class InJavaScriptLocalObj {
            @JavascriptInterface
            public void showSource(String html) {
                LogUtils.info("myHTML", html);
            }
        }

        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            final PayTask task = new PayTask(WebViewActivity.this);
            /**处理订单信息 9000——订单支付成功
             8000——正在处理中
             4000——订单支付失败
             5000——重复请求
             6001——用户中途取消
             6002——网络连接出错
             */
            final String ex = task.fetchOrderInfoFromH5PayUrl(url);
            if (!TextUtils.isEmpty(ex)) {
                //调用支付接口进行支付
                new Thread(new Runnable() {
                    public void run() {
                        H5PayResultModel result = task.h5Pay(ex, true);
                        //处理返回结果
                        if (!TextUtils.isEmpty(result.getReturnUrl())) {
                            view.loadUrl(result.getReturnUrl());
                        }
                    }
                }).start();
            } else {
                view.loadUrl(url);
            }

            return true;
        }


    }
}
