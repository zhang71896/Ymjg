package com.yrj520.pfapp.ymjg.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.application.SuperApplication;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;

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
    private int myType=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_order, container, false);
        iv_order=(ImageView) viewContent.findViewById(R.id.iv_order);
        order_lv=(ListView) viewContent.findViewById(R.id.order_lv);
        initDatas();
        return viewContent;
    }

    private void initDatas() {
        UserApi.OrderListApi(SuperApplication.getInstance().getApplicationContext(),myType+"", new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                LogUtils.info("orderList: ",response.toString());
                String code=response.optString("code");
                if(code.equals("200")){

                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    public void setType(int type){
        myType=type;
    }

}
