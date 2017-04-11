package com.yrj520.pfapp.ymjg.UI.utils;

import android.app.Activity;
import android.view.View;

import com.yrj520.pfapp.ymjg.UI.entity.ThridGoodsData;
import com.yrj520.pfapp.ymjg.UI.popwindow.CartPopWindow;

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

    public static void showCartPopWindow(ThridGoodsData.DataBean dataBean, Activity mContext,View parent){
        CartPopWindow cartPopWindow=new CartPopWindow(mContext);
        cartPopWindow.InitDatas(dataBean);
        cartPopWindow.showPopupWindow(parent);
    }
}
