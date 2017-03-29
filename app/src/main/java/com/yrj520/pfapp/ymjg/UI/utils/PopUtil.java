package com.yrj520.pfapp.ymjg.UI.utils;

import android.app.Activity;
import android.view.View;
import android.widget.PopupWindow;

import com.yrj520.pfapp.ymjg.UI.entity.OrderData;
import com.yrj520.pfapp.ymjg.UI.entity.ThridGoodsData;
import com.yrj520.pfapp.ymjg.UI.popwindow.CartPopWindow;
import com.yrj520.pfapp.ymjg.UI.popwindow.GoodDetailPopWindow;
import com.yrj520.pfapp.ymjg.UI.popwindow.GoodSizePopWindow;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class PopUtil {

    private static PopupWindow currentPopWindow;

    public static void ShowGoodSizePopWindow(ThridGoodsData.DataBean dataBean, Activity mContext,View parent){
        //HiddenCurrentWindow();
        GoodSizePopWindow goodSizePopWindow=new GoodSizePopWindow(mContext);
        goodSizePopWindow.InitDatas(dataBean);
        goodSizePopWindow.showPopupWindow(parent);
        //currentPopWindow=goodSizePopWindow;
    }

    public static void ShowShopCartPopWindow(Activity mContext,View parent){
        //HiddenCurrentWindow();
        CartPopWindow cartPopWindow=new CartPopWindow(mContext);
        cartPopWindow.InitDatas();
        cartPopWindow.showPopupWindow(parent);
        //currentPopWindow=cartPopWindow;
    }

    public static void ShowGoodDetailPopWindow(Activity mContext, View parent, List<OrderData.DataBean.SpecBean > specBeanList){
        GoodDetailPopWindow goodDetail=new GoodDetailPopWindow(mContext);
        goodDetail.InitDatas(specBeanList);
        goodDetail.showPopupWindow(parent);
    }


    private static void HiddenCurrentWindow(){
        if(currentPopWindow!=null){
            LogUtils.info("currentPopWindow",currentPopWindow.isShowing()+"");
            currentPopWindow.dismiss();
        }
    }
}
