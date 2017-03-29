package com.yrj520.pfapp.ymjg.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.OrderAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.application.SuperApplication;
import com.yrj520.pfapp.ymjg.UI.entity.OrderData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseFragment;

import org.json.JSONObject;

import okhttp3.Request;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class OrderFragment extends BaseFragment {
    private View viewContent;
    private RelativeLayout rl_no_order;
    private ListView order_lv;
    private OrderAdapter orderAdapter;
    private OrderData mOrderData;
    private int myType=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_order, container, false);
        initViews();
        return viewContent;
    }

    public View getView(){
        return viewContent;
    }

    private void initViews() {
        rl_no_order=(RelativeLayout) viewContent.findViewById(R.id.rl_no_order);
        order_lv=(ListView) viewContent.findViewById(R.id.order_lv);
        orderAdapter=new OrderAdapter(getActivity());
        order_lv.setAdapter(orderAdapter);
    }

    public void initDatas() {
        UserApi.OrderListApi(SuperApplication.getInstance().getApplicationContext(),myType+"", new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code="";
                code=response.optString("code");
                if(code.equals("200")){
                    Gson gson=new Gson();
                    mOrderData=gson.fromJson(response.toString(),OrderData.class);
                    setViews();
                }else {
                    ToastUtils.showShort(getActivity(), "没有数据了。。");
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
    private void setViews() {
        if(mOrderData.getData().size()>0){
            rl_no_order.setVisibility(View.GONE);
        }else{
            rl_no_order.setVisibility(View.VISIBLE);
        }
        orderAdapter.addAll(mOrderData,myType);
    }

    public void setType(int type){
        myType=type;
    }

}
