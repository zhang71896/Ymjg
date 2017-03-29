package com.yrj520.pfapp.ymjg.UI.popwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.entity.GoodSize;
import com.yrj520.pfapp.ymjg.UI.entity.ThridGoodsData;
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

public class CartPopWindow extends PopupWindow {

    private Context mContext;
    private View view;
    private ThridGoodsData.DataBean mDataBean;
    private GoodSize goodSize;
    private  static CartPopWindow mCartPopWindow=null;
    private ImageView iv_pic;
    private TextView tv_good_name;
    private  TextView tv_good_num;
    private  TextView tv_now_price;
    private  TextView tv_shop_price;
    private ListView lv_size_list;


    public CartPopWindow(Context context){
        mContext=context;
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_cart, null);
        iv_pic=(ImageView)view.findViewById(R.id.iv_pic);
        tv_good_name=(TextView)view.findViewById(R.id.tv_good_name);
        tv_good_num=(TextView)view.findViewById(R.id.tv_good_num);
        tv_now_price=(TextView)view.findViewById(R.id.tv_now_price);
        tv_shop_price=(TextView)view.findViewById(R.id.tv_shop_price);
        lv_size_list=(ListView) view.findViewById(R.id.lv_size_list);
    }

    public void  InitDatas(ThridGoodsData.DataBean dataBean){
        mDataBean=dataBean;
        String sizeId=mDataBean.getGoods_id().toString();
        UserApi.GetGoodsSpecApi(mContext, sizeId, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                LogUtils.info("GetGoodsSpecApi",response.toString());
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

    }
    public static CartPopWindow getInstance(Context mContext){
        if(mCartPopWindow==null){
            mCartPopWindow=new CartPopWindow(mContext);
        }
        return mCartPopWindow;
    }
}
