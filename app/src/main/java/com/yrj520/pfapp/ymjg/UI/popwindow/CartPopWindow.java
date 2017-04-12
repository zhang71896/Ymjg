package com.yrj520.pfapp.ymjg.UI.popwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.ShopCartAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.ShopCartData;
import com.yrj520.pfapp.ymjg.UI.event.CartRefreshEvent;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *购物车弹出窗
 * @author Rock
 * @version 1.0
 */

public class CartPopWindow extends PopupWindow {
    private Activity mContext;
    private View view;
    private ListView lv_good_list;
    private RelativeLayout rl_clear_cart;
    private ShopCartData mShopCartData;
    private ShopCartAdapter mShopCartAdapter;

    public CartPopWindow(final Activity context){
        mContext=context;
        mContext=context;
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_cart, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(h/2);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);

        rl_clear_cart=(RelativeLayout) view.findViewById(R.id.rl_clear_cart);

        lv_good_list=(ListView)view.findViewById(R.id.lv_good_list);

        mShopCartAdapter=new ShopCartAdapter(mContext);

        lv_good_list.setAdapter(mShopCartAdapter);

        rl_clear_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApi.ClearShoppingCatApi(mContext, new HttpUtil.RequestBack() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        String code=response.optString("code");
                        String meg=response.optString("meg");
                        ToastUtils.showShort(mContext,meg);
                        if(code.equals("200")){
                            mShopCartAdapter.clearAll();
                            CartRefreshEvent cartRefreshEvent =new CartRefreshEvent(MyConstant.UpdateShopCart);
                            EventBus.getDefault().post(cartRefreshEvent);
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
    }

    public void InitDatas(){
        UserApi.ShoppingCatApi(mContext, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                if(code.equals("200")){
                    Gson gson=new Gson();
                    mShopCartData=gson.fromJson(response.toString(),ShopCartData.class);
                    setViews();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 0);
        } else {
            this.dismiss();
        }
    }

    private void setViews(){
        mShopCartAdapter.addAll(mShopCartData);
    }
}
