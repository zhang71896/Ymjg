package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.OrderFragmentAdapter;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.event.CartRefreshEvent;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * 订单管理
 * @author Rock
 * @version 1.0
 */

public class OrderCooperateActivity extends BaseActivity {

    private TabLayout tab_essence;
    private ViewPager vp_essence;
    private OrderFragmentAdapter mOrderFragmentAdapter;

    public static int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    private static int mPosition=0;
    private TextView tv_left;
    private  TextView tv_center;
    List<String> titles=new ArrayList<String>();
    String[] titlesGroup={"全部","待付款","待收货","已完成","已取消"};
    public static int [] titlesIndex={1,0,2,4,3};
    private static Activity activity;
    private RadioButton rb_goods;
    public static  Activity getActivity(){
        return activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        activity=this;
        EventBus.getDefault().register(this);
        initViews();
        initClickListenner();
        initDatas();
    }

    public void onEventMainThread(CartRefreshEvent cartRefreshEvent){
        String msgType=cartRefreshEvent.getMsg();
        if(msgType.equals(MyConstant.UpdateShopCart)){
            //获取购物车相关的信息
            return;
        }
    }

    private void initDatas() {
        titles.clear();
        for(int i=0;i<titlesGroup.length;i++) {
            titles.add(titlesGroup[i]);
        }
        mOrderFragmentAdapter=new OrderFragmentAdapter(getSupportFragmentManager(),titles);
        vp_essence.setAdapter(mOrderFragmentAdapter);

        //将TabLayout和ViewPager关联起来
        tab_essence.setupWithViewPager(vp_essence);
        tab_essence.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPosition=tab.getPosition();
                vp_essence.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp_essence.setCurrentItem(1);
    }

    private void initClickListenner() {
        rb_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(OrderCooperateActivity.this,PurchaseGoodActivity.class);
                startActivity(intent);
            }
        });
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews(){
        rb_goods=(RadioButton) findViewById(R.id.rb_goods);
        tab_essence = (TabLayout)findViewById(R.id.tab_essence);
        vp_essence = (ViewPager) findViewById(R.id.vp_essence);
        tv_center=(TextView)findViewById(R.id.tv_center);
        tv_left=(TextView)findViewById(R.id.tv_left);
        tv_center.setText("订单管理");

    }
}
