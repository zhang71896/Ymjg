package com.yrj520.pfapp.ymjg.UI.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.SecondClassGoodAdapter;
import com.yrj520.pfapp.ymjg.UI.adapter.ThridClassGoodAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.application.SuperApplication;
import com.yrj520.pfapp.ymjg.UI.entity.OneTwoClassGoodData;
import com.yrj520.pfapp.ymjg.UI.entity.ThridGoodsData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.PurchaseGoodActivity;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Request;

/**
 * Created by zry on 17/4/2.
 */

public class GoodsFragment extends Fragment {

    private View viewContent;

    private  List<OneTwoClassGoodData.DataBean.ArrayBean> mArrayBeanList;

    private ListView lv_second_goods;

    private GridView gv_thrid_goods;

    private  SecondClassGoodAdapter secondAdapter;

    private  ThridClassGoodAdapter thridAdapter;

    private  ThridGoodsData thridGoodsData;

    private   int mSecondGoodPoistion=0;

    private   int mFirstPosition=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_goods, container, false);
        initViews();
        initAdapter();
        initDatas();
        return viewContent;
    }

    public void initDatas() {
        String pid=PurchaseGoodActivity.getOneTwoClassGoodData().getData().get(mFirstPosition).getArray().get(mSecondGoodPoistion).getCid();
        if(!StringUtils.isEmpty(pid)) {
            UserApi.Get3GoodsApi(SuperApplication.getInstance().getApplicationContext(), pid, new HttpUtil.RequestBack() {
                @Override
                public void onSuccess(JSONObject response) {
                    String code = response.optString("code");
                    if (code.equals("200")) {
                        Gson gson = new Gson();
                        thridGoodsData=gson.fromJson(response.toString(),ThridGoodsData.class);
                        List<ThridGoodsData.DataBean> dataBeanList=thridGoodsData.getData();
                        thridAdapter.clearAll();
                        thridAdapter.addAll(dataBeanList);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSecondGoodPoistion=0;
        mFirstPosition=0;
    }

    private void initAdapter() {
        secondAdapter=new SecondClassGoodAdapter(SuperApplication.getInstance().getApplicationContext());
        mFirstPosition=PurchaseGoodActivity.getFirstGoodPosition();
        mArrayBeanList= PurchaseGoodActivity.getOneTwoClassGoodData().getData().get(mFirstPosition).getArray();
        lv_second_goods.setAdapter(secondAdapter);
        if(mArrayBeanList!=null) {
            secondAdapter.addAll(mArrayBeanList);
        }
        secondAdapter.setSelectedIndex(0);
        thridAdapter=new ThridClassGoodAdapter(getActivity());
        gv_thrid_goods.setAdapter(thridAdapter);
        gv_thrid_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                thridAdapter.ClickPositionListener(position,viewContent);
            }
        });
    }

    private void initViews() {
        lv_second_goods = (ListView) viewContent.findViewById(R.id.lv_second_goods);
        gv_thrid_goods = (GridView) viewContent.findViewById(R.id.gv_thrid_goods);
    }

    public void ChangeThridGoodsFragment(int position){
        mSecondGoodPoistion=position;
        initDatas();
    }

    public void SetFirstPosition(int position){
        mFirstPosition=position;
    }





}

