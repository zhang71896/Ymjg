package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.GoodFragmentAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.entity.OneTwoClassGoodData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

public class PurchaseGoodActivity extends BaseActivity {
    /**
     * FragmentTabhost
     */


    private TabLayout tab_essence;


    private ViewPager vp_essence;

    private static int mPosition=0;

    private static  OneTwoClassGoodData  mOneTwoClassGoodData;

    private List<String> titles = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        initViews();
        initDatas();

    }

    private void setViews() {
        List<String> firstGoodtitles=new ArrayList<String>();
        for(int i=0;i<mOneTwoClassGoodData.getData().size();i++){
            firstGoodtitles.add(mOneTwoClassGoodData.getData().get(i).getName());
        }
        GoodFragmentAdapter goodFragmentAdapter=new GoodFragmentAdapter(getSupportFragmentManager(),firstGoodtitles,mOneTwoClassGoodData);

        vp_essence.setAdapter(goodFragmentAdapter);

        //将TabLayout和ViewPager关联起来
        tab_essence.setupWithViewPager(vp_essence);



        tab_essence.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                vp_essence.setCurrentItem(tab.getPosition());

        }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        for (int i = 0; i < tab_essence.getTabCount(); i++) {
//            TabLayout.Tab tab = tab_essence.getTabAt(i);
//            if (tab != null) {
//                tab.setCustomView((View) goodFragmentAdapter.instantiateItem(tab_essence,i));
//                if (tab.getCustomView() != null) {
//                    View tabView = (View) tab.getCustomView().getParent();
//                    tabView.setTag(i);
//                    tabView.setOnClickListener(mTabOnClickListener);
//                }
//            }
//        }

    }


    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                int pos = (int) view.getTag();
                ToastUtils.showShort(PurchaseGoodActivity.this,"hi");
                TabLayout.Tab tab = tab_essence.getTabAt(pos);
                if (tab != null) {


            }
        }
    };

    public static OneTwoClassGoodData getOneTwoClassGoodData(){

        return mOneTwoClassGoodData;
    }

    public static int getFirstGoodPosition(){
        return mPosition;
    }

    public static void setFirstGoodPosition(int position){
        mPosition=position;
    }

    private void initLinearLayout() {
        LinearLayout layout=new LinearLayout(this);
        LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);//定义布局管理器的参数
        layout.setOrientation(LinearLayout.VERTICAL);//所有组件垂直摆放

    }



    private void initDatas() {
        UserApi.Get12GoodsDirectlyApi(PurchaseGoodActivity.this,new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                LogUtils.info("response.Result: ",response.toString());
                String code=response.optString("code");
                //获取数据成功
                if(code.equals("200")) {
                    ToastUtils.showShort(PurchaseGoodActivity.this, "获取数据成功!");
                    Gson gson = new Gson();
                    mOneTwoClassGoodData = gson.fromJson(response.toString(), OneTwoClassGoodData.class);
                    setViews();
                    return;
                }
                //获取失败
                ToastUtils.showShort(PurchaseGoodActivity.this,"获取数据失败!");
            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                showLoading("获取数据中....");
            }

            @Override
            public void onAfter() {
                super.onAfter();
                closeLoading();
            }
        });
    }

    private void initViews() {
        tab_essence = (TabLayout)findViewById(R.id.tab_essence);
         vp_essence = (ViewPager) findViewById(R.id.vp_essence);
    }



}
