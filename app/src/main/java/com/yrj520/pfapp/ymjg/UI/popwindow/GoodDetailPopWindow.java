package com.yrj520.pfapp.ymjg.UI.popwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.GoodDetailAdapter;
import com.yrj520.pfapp.ymjg.UI.entity.OrderData;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *订单界面当中的商品详情界面
 * @author Rock
 * @version 1.0
 */

public class GoodDetailPopWindow extends PopupWindow {
    private Activity mContext;
    private View view;
    private ListView lv_good_list;
    private GoodDetailAdapter goodDetailAdapter;
    private TextView tv_left;
    private TextView tv_center;
    public GoodDetailPopWindow(final Activity context){
        mContext=context;
        view = LayoutInflater.from(mContext).inflate(R.layout.good_detail_list, null);
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
        ColorDrawable dw = new ColorDrawable(mContext.getResources().getColor(R.color.gray_e5));
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
        lv_good_list=(ListView) view.findViewById(R.id.lv_good_list);
        tv_left=(TextView)view.findViewById(R.id.tv_left);
        tv_center=(TextView)view.findViewById(R.id.tv_center);
        tv_center.setText("详细商品列表");
        goodDetailAdapter=new GoodDetailAdapter(mContext);
        lv_good_list.setAdapter(goodDetailAdapter);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidden();
            }
        });
    }

    public void InitDatas(List<OrderData.DataBean.SpecBean > specBeanList){
        goodDetailAdapter.addAll(specBeanList);
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAtLocation(parent, Gravity.RIGHT | Gravity.BOTTOM, 0, 0);  //右下
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

    private void hidden(){
        this.dismiss();
    }
}
