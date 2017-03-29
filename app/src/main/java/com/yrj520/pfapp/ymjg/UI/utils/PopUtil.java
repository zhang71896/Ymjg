package com.yrj520.pfapp.ymjg.UI.utils;

import android.content.Context;

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

    public static void showCartPopWindow(ThridGoodsData.DataBean dataBean, Context mContext){
        CartPopWindow cartPopWindow=CartPopWindow.getInstance(mContext);
        cartPopWindow.InitDatas(dataBean);

    }
}
