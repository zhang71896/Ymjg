package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.GoodFragmentAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.OneTwoClassGoodData;
import com.yrj520.pfapp.ymjg.UI.entity.ShopCartData;
import com.yrj520.pfapp.ymjg.UI.event.CartRefreshEvent;
import com.yrj520.pfapp.ymjg.UI.event.PersonalMessagEvent;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.PopUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
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

    private RelativeLayout rl_left;

    private  TextView tv_center;

    private TextView tv_total_price;

    private  TextView tv_total_goods_num;

    private ViewPager vp_essence;

    private ImageView iv_cart;

    private RelativeLayout rl_shop_cart;

    private TextView tv_produce_order;

    private RadioButton rb_order_manage;

    private static RelativeLayout rl_bottom_menu;

    private static int mPosition=0;

    private static  OneTwoClassGoodData  mOneTwoClassGoodData;

    private List<String> titles = new ArrayList<>();

    private GoodFragmentAdapter goodFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        EventBus.getDefault().register(this);
        initViews();
        initClickListenner();
        initDatas(0);
        GetShopCart();
    }

    public void onEventMainThread(CartRefreshEvent cartRefreshEvent){
        String msgType=cartRefreshEvent.getMsg();
        if(msgType.equals(MyConstant.UpdateShopCart)){
            //获取购物车相关的信息
            initDatas(1);
            GetShopCart();
            return;
        }
    }




    private void GetShopCart() {
        UserApi.ShoppingCatApi(PurchaseGoodActivity.this, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                if(code.equals("200")){
                    Gson gson=new Gson();
                    ShopCartData shopCartData=gson.fromJson(response.toString(),ShopCartData.class);
                    String sumprice=response.optString("sumprice");
                    String goods_num=response.optString("goods_num");
                    if(!StringUtils.isEmpty(goods_num)) {
                        tv_total_goods_num.setText("共计商品" + goods_num + "件");
                    }else
                    {
                        tv_total_goods_num.setText("共计商品" + 0 + "件");
                    }
                    if(!StringUtils.isEmpty(sumprice)) {
                        if(sumprice.equals("null"))
                        {
                            sumprice="0";
                        }
                        tv_total_price.setText("¥ " + sumprice);
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }

        });
    }

    private void setViews() {
        List<String> firstGoodtitles=new ArrayList<String>();
        for(int i=0;i<mOneTwoClassGoodData.getData().size();i++){
            firstGoodtitles.add(mOneTwoClassGoodData.getData().get(i).getName());
        }
        goodFragmentAdapter=new GoodFragmentAdapter(getSupportFragmentManager(),firstGoodtitles,mOneTwoClassGoodData);
        vp_essence.setAdapter(goodFragmentAdapter);

        //将TabLayout和ViewPager关联起来
        tab_essence.setupWithViewPager(vp_essence);
        tab_essence.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPosition=tab.getPosition();
                vp_essence.setCurrentItem(tab.getPosition());
                goodFragmentAdapter.refreshAllDatas(mPosition);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPosition=0;
        EventBus.getDefault().unregister(this);
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


    public static View getBottomView(){
        return rl_bottom_menu;
    }


    private void initDatas(final int mType) {
        UserApi.Get12GoodsDirectlyApi(PurchaseGoodActivity.this,new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code="";
                if(response!=null){

                }
                code=response.optString("code");
                //获取数据成功
                if(code.equals("200")) {
                    ToastUtils.showShort(PurchaseGoodActivity.this, "获取数据成功!");
                    Gson gson = new Gson();
                    mOneTwoClassGoodData = gson.fromJson(response.toString(), OneTwoClassGoodData.class);
                    if(mType==0) {
                        setViews();
                    }else{
                        goodFragmentAdapter.refrashDatas(mPosition);
                    }

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
        rl_shop_cart=(RelativeLayout) findViewById(R.id.rl_shop_cart);
        rb_order_manage=(RadioButton) findViewById(R.id.rb_order_manage);
        rl_bottom_menu=(RelativeLayout)findViewById(R.id.rl_bottom_menu);
        tv_produce_order=(TextView)findViewById(R.id.tv_produce_order);
        iv_cart=(ImageView)findViewById(R.id.iv_cart);
        tv_produce_order=(TextView)findViewById(R.id.tv_produce_order);
        tv_total_price=(TextView)findViewById(R.id.tv_total_price);
        tv_total_goods_num=(TextView)findViewById(R.id.tv_total_goods_num);
        tab_essence = (TabLayout)findViewById(R.id.tab_essence);
         vp_essence = (ViewPager) findViewById(R.id.vp_essence);
        rl_left=(RelativeLayout) findViewById(R.id.rl_left);
        tv_center=(TextView)findViewById(R.id.tv_center);
        tv_center.setText("商品进购");
    }

    private void initClickListenner(){
        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.ShowShopCartPopWindow(PurchaseGoodActivity.this,PurchaseGoodActivity.getBottomView());
            }
        });

        tv_produce_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProduceOrder();
            }
        });
        rb_order_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(PurchaseGoodActivity.this,OrderCooperateActivity.class);
                startActivity(intent);
            }
        });

        rl_shop_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.ShowShopCartPopWindow(PurchaseGoodActivity.this,PurchaseGoodActivity.getBottomView());
            }
        });
    }

    /**
     * 生成订单
     */
    private void ProduceOrder(){
        UserApi.ProduceOrderApi(PurchaseGoodActivity.this, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(PurchaseGoodActivity.this,meg);
                if(code.equals("200")){
                    //获取购物车
                    GetShopCart();
                    //获取商品选中状态
                    goodFragmentAdapter.refrashDatas(mPosition);
                    //修改主页个人信息
                    EventBus.getDefault().post(new PersonalMessagEvent(MyConstant.UpdatePersonalMessage));
                    Intent intent=new Intent(PurchaseGoodActivity.this,OrderCooperateActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onError(Exception e) {

            }
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                showLoading("正在生成中....");
            }

            @Override
            public void onAfter() {
                super.onAfter();
                closeLoading();
            }
        });
    }



}
