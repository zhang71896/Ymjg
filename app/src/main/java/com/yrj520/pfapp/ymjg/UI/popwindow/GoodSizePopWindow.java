package com.yrj520.pfapp.ymjg.UI.popwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.GoodSpecAdapter;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.GoodSizeData;
import com.yrj520.pfapp.ymjg.UI.entity.ThridGoodsData;
import com.yrj520.pfapp.ymjg.UI.event.CartRefreshEvent;
import com.yrj520.pfapp.ymjg.UI.filter.InputMaxLimitFilter;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ImageUtils;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *商品规格弹出框
 * @author Rock
 * @version 1.0
 */

public class GoodSizePopWindow extends PopupWindow {
    private static Activity mContext;
    private View view;
    //private ThridGoodsData.DataBean mDataBean;
    private GoodSizeData goodSize;
    private GoodSpecAdapter goodSpecAdapter;
    private  static GoodSizePopWindow mCartPopWindow=null;
    private ImageView iv_pic;
    private TextView tv_good_name;
    private  TextView tv_good_num;
    private  TextView tv_now_price;
    private  TextView tv_shop_price;
    private  TextView tv_good_size;
    private RelativeLayout rl_contetnt;
    private RelativeLayout rl_add;
    private RelativeLayout rl_minus;
    private EditText et_store_num;
    private ListView lv_size_list;
    //是否有明细
    private boolean hasSpec=false;
    private int mGoodNum;
    private String good_id;

    /**
     *   创建商品尺寸弹出框
     * @param context 上下文对象
     *
     */
    public GoodSizePopWindow(Activity context){
        mContext=context;
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_good_size, null);
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
        tv_good_size=(TextView)view.findViewById(R.id.tv_good_size);
        rl_contetnt=(RelativeLayout) view.findViewById(R.id.rl_contetnt);
        rl_add=(RelativeLayout) view.findViewById(R.id.rl_add);
        rl_minus=(RelativeLayout) view.findViewById(R.id.rl_minus);
        et_store_num=(EditText) view.findViewById(R.id.et_store_num);
        iv_pic=(ImageView)view.findViewById(R.id.iv_pic);
        tv_good_name=(TextView)view.findViewById(R.id.tv_good_name);
        tv_good_num=(TextView)view.findViewById(R.id.tv_good_num);
        tv_now_price=(TextView)view.findViewById(R.id.tv_now_price);
        tv_shop_price=(TextView)view.findViewById(R.id.tv_shop_price);
        lv_size_list=(ListView) view.findViewById(R.id.lv_size_list);

        goodSpecAdapter=new GoodSpecAdapter(mContext);
        lv_size_list.setAdapter(goodSpecAdapter);
        initClickListenner();
    }

    private void initClickListenner() {
        rl_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodNum++;
                OperateGoodsNum();
            }
        });

        rl_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGoodNum > 0) {
                    mGoodNum--;
                } else {
                    mGoodNum = 0;
                }
                OperateGoodsNum();
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


    private void OperateGoodsNum() {
        if(mGoodNum>=0) {
            String goodsNum = mGoodNum + "";
            UserApi.OperateGoodsNumApi(mContext, good_id, "", goodsNum, new HttpUtil.RequestBack() {
                @Override
                public void onSuccess(JSONObject response) {
                    String code = response.optString("code");
                    String goods_num = "0";
                    if (code.equals("200")) {
                        goods_num = response.optString("goods_num");
                        int num = Integer.parseInt(goods_num);
                        et_store_num.setText(goods_num);
                        CartRefreshEvent cartRefreshEvent =new CartRefreshEvent(MyConstant.UpdateShopCart);
                        EventBus.getDefault().post(cartRefreshEvent);
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
    }



    public void  InitDatas(ThridGoodsData.DataBean dataBean){
        good_id=dataBean.getGoods_id().toString();
        UserApi.GetGoodsSpecApi(mContext, good_id, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                if(code.equals("200")){
                     Gson gson=new Gson();
                     goodSize=gson.fromJson(response.toString(),GoodSizeData.class);
                     setViews();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    private void setViews(){
        if(goodSize.getData().getSpec().size()>0){
            hasSpec=true;
            tv_good_size.setText("规格列表");
        }else{
            hasSpec=false;
            tv_good_size.setText("该商品为单品");
        }
        if(hasSpec){
            rl_contetnt.setVisibility(View.GONE);
        }
        if(!StringUtils.isEmpty(goodSize.getData().getImage_url())) {
            ImageUtils.loadCommonPic(mContext, goodSize.getData().getImage_url(), iv_pic);
        }

        if(!StringUtils.isEmpty(goodSize.getData().getGoods_name())){
            tv_good_name.setText(goodSize.getData().getGoods_name());
        }
        String storeNum="999";
        if(!StringUtils.isEmpty(goodSize.getData().getSumstore_count())){
            tv_good_num.setText("总库存: "+goodSize.getData().getSumstore_count());

        }
        et_store_num.setFilters(new InputFilter[]{new InputMaxLimitFilter("0", storeNum)});
        et_store_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!StringUtils.isEmpty(et_store_num.getText())) {
                    mGoodNum = Integer.parseInt(et_store_num.getText().toString());
                    OperateGoodsNum();
                }
            }
        });

        if(!StringUtils.isEmpty(goodSize.getData().getShop_price())){
            tv_now_price.setText("¥ "+goodSize.getData().getShop_price());
        }

        if(!StringUtils.isEmpty(goodSize.getData().getShop_price())){
            tv_shop_price.setText("¥ "+goodSize.getData().getMarket_price());
        }

        if(!StringUtils.isEmpty(goodSize.getData().getImage_url())){
            String ImageUrl=ImageUtils.getImageUrl(goodSize.getData().getImage_url());
            ImageUtils.loadCommonPic(mContext,ImageUrl,iv_pic);
        }
        goodSpecAdapter.addAll(goodSize);

    }
}
