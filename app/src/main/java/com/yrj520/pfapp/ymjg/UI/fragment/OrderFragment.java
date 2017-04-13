package com.yrj520.pfapp.ymjg.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.OrderAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.application.SuperApplication;
import com.yrj520.pfapp.ymjg.UI.entity.OrderData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;

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

public class OrderFragment extends Fragment {
    private View viewContent;
    private ImageView iv_order;
    private ListView order_lv;
    private OrderAdapter orderAdapter;
    private OrderData mOrderData;
    private int myType=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_order, container, false);
        initViews();
        initDatas();
        return viewContent;
    }

    public View getView(){
        return viewContent;
    }

    private void initViews() {
        iv_order=(ImageView) viewContent.findViewById(R.id.iv_order);
        order_lv=(ListView) viewContent.findViewById(R.id.order_lv);
        orderAdapter=new OrderAdapter(SuperApplication.getInstance().getApplicationContext());
        order_lv.setAdapter(orderAdapter);
    }

    public void initDatas() {
        UserApi.OrderListApi(SuperApplication.getInstance().getApplicationContext(),myType+"", new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                if(code.equals("200")){
                    Gson gson=new Gson();
                    mOrderData=gson.fromJson(response.toString(),OrderData.class);
                    setViews();
                }
            }
            @Override
            public void onError(Exception e) {

            }
        });

    }
    private void setViews() {
        if(mOrderData.getData().size()>0){
            iv_order.setVisibility(View.GONE);
        }else{
            iv_order.setVisibility(View.VISIBLE);
        }
        orderAdapter.addAll(mOrderData,myType);
    }

    public void setType(int type){
        myType=type;
    }

}
