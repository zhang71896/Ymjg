package com.yrj520.pfapp.ymjg.UI.popwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.GoodSpecAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.entity.GoodSize;
import com.yrj520.pfapp.ymjg.UI.entity.ThridGoodsData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ImageUtils;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;

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

public class CartPopWindow extends PopupWindow {

    private Activity mContext;
    private View view;
    private ThridGoodsData.DataBean mDataBean;
    private GoodSize goodSize;
    private GoodSpecAdapter goodSpecAdapter;
    private  static CartPopWindow mCartPopWindow=null;
    private ImageView iv_pic;
    private TextView tv_good_name;
    private  TextView tv_good_num;
    private  TextView tv_now_price;
    private  TextView tv_shop_price;
    private ListView lv_size_list;


    public CartPopWindow(Activity context){
        mContext=context;
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_cart, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(h);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
        iv_pic=(ImageView)view.findViewById(R.id.iv_pic);
        tv_good_name=(TextView)view.findViewById(R.id.tv_good_name);
        tv_good_num=(TextView)view.findViewById(R.id.tv_good_num);
        tv_now_price=(TextView)view.findViewById(R.id.tv_now_price);
        tv_shop_price=(TextView)view.findViewById(R.id.tv_shop_price);
        lv_size_list=(ListView) view.findViewById(R.id.lv_size_list);
        goodSpecAdapter=new GoodSpecAdapter(mContext);
        lv_size_list.setAdapter(goodSpecAdapter);
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 0);
        } else {
            this.dismiss();
        }
    }



    public void  InitDatas(ThridGoodsData.DataBean dataBean){
        mDataBean=dataBean;
        String sizeId=mDataBean.getGoods_id().toString();
        UserApi.GetGoodsSpecApi(mContext, sizeId, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                if(code.equals("200")){
                    Gson gson=new Gson();
                     goodSize=gson.fromJson(response.toString(),GoodSize.class);
                     setViews();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    private void setViews(){
        if(!StringUtils.isEmpty(mDataBean.getImage_url())) {
            ImageUtils.loadCommonPic(mContext, mDataBean.getImage_url(), iv_pic);
        }

        if(!StringUtils.isEmpty(mDataBean.getGoods_name())){
            tv_good_name.setText(mDataBean.getGoods_name());
        }

        if(!StringUtils.isEmpty(mDataBean.getGoods_num())){
            tv_good_num.setText("总库存: "+mDataBean.getGoods_num());
        }

        if(!StringUtils.isEmpty(mDataBean.getShop_price())){
            tv_now_price.setText("¥ "+mDataBean.getShop_price());
        }

        if(!StringUtils.isEmpty(mDataBean.getMarket_price())){
            tv_shop_price.setText("¥ "+mDataBean.getMarket_price());
        }

        if(!StringUtils.isEmpty(mDataBean.getImage_url())){
            String ImageUrl=ImageUtils.getImageUrl(mDataBean.getImage_url());
            ImageUtils.loadCommonPic(mContext,ImageUrl,iv_pic);
        }
        goodSpecAdapter.addAll(goodSize);



    }
    public static CartPopWindow getInstance(Activity mContext){
        if(mCartPopWindow==null){
            mCartPopWindow=new CartPopWindow(mContext);
        }
        return mCartPopWindow;
    }
}
