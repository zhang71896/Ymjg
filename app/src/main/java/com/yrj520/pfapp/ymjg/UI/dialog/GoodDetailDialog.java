package com.yrj520.pfapp.ymjg.UI.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jock.pickerview.adapter.DIalogGoodDetailAdapter;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.application.SuperApplication;
import com.yrj520.pfapp.ymjg.UI.entity.GoodSpecData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Request;

/**
 * Created by zry on 17/5/16.
 */

public class GoodDetailDialog extends Dialog {

    private Context mContext;

    private String mOrderId;

    private ListView mListView;

    private DIalogGoodDetailAdapter dialogGoodDetailAdapter;

    private List<GoodSpecData.DataBean.AttrBean>  AttryBeanList;

    public GoodDetailDialog(Context context,String orderId){
        super(context);
        mContext=context;
        mOrderId=orderId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_good_detail);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        mListView=(ListView) findViewById(R.id.good_detail_list);
        dialogGoodDetailAdapter=new DIalogGoodDetailAdapter(getContext());
        mListView.setAdapter(dialogGoodDetailAdapter);
        initDatas();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    private void initDatas() {
        UserApi.GetGoodsSpecApi(SuperApplication.getInstance().getApplicationContext(), mOrderId, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                if(code.equals("200")){
                    Gson gson=new Gson();
                    GoodSpecData goodSpecData=gson.fromJson(response.toString(),GoodSpecData.class);
                    AttryBeanList=goodSpecData.getData().getAttr();
                    setViews();
                }
            }

            private void setViews() {
                if(AttryBeanList!=null&&AttryBeanList.size()>0) {
                    dialogGoodDetailAdapter.AddAll(AttryBeanList);
                }else{
                    ToastUtils.showShort(mContext,"客官没有详细数据啦！");
                }
            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                super.onAfter();
            }
        });

    }
}
