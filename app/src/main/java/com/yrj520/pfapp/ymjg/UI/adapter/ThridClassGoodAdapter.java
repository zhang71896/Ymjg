package com.yrj520.pfapp.ymjg.UI.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.entity.ThridGoodsData;
import com.yrj520.pfapp.ymjg.UI.utils.ImageUtils;
import com.yrj520.pfapp.ymjg.UI.utils.PopUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.PurchaseGoodActivity;

import java.util.ArrayList;
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

public class ThridClassGoodAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Activity mContext;
    private List<ThridGoodsData.DataBean> dataBeanList=new ArrayList<ThridGoodsData.DataBean>();

    public void clearAll(){
        dataBeanList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ThridGoodsData.DataBean> list) {
        dataBeanList.addAll(list);
        notifyDataSetChanged();
    }
    public void ClickPositionListener(int position,View parent){
        ThridGoodsData.DataBean dataBean=getItem(position);
        PopUtil.showCartPopWindow(dataBean,mContext, PurchaseGoodActivity.getBottomView());
    }
    public ThridClassGoodAdapter(Activity context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public ThridGoodsData.DataBean getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ThridClassGoodAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_grid, null);
            holder = new ThridClassGoodAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ThridClassGoodAdapter.ViewHolder) convertView.getTag();
        }
        final ThridGoodsData.DataBean data = getItem(position);
        if(!StringUtils.isEmpty(data.getImage_url())) {
            ImageUtils.loadCommonPic(mContext, ImageUtils.getImageUrl(data.getImage_url()), holder.iv_pic);
        }
        if(!StringUtils.isEmpty(data.getGoods_name())) {
            holder.good_name.setText(data.getGoods_name());
        }
        if(!StringUtils.isEmpty(data.getMarket_price())) {
            holder.tv_market_price_value.setText(" ¥ "+data.getMarket_price());
        }
        if(!StringUtils.isEmpty(data.getShop_price())) {
            holder.tv_now_price_value.setText(" ¥ "+data.getShop_price());
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_pic;
        private TextView good_name;
        private TextView tv_market_price_value;
        private TextView tv_now_price_value;

        final View root;
        ViewHolder(View root) {
            this.root = root;
            iv_pic=(ImageView)root.findViewById(R.id.iv_pic);
            good_name=(TextView)root.findViewById(R.id.good_name);
            tv_market_price_value=(TextView)root.findViewById(R.id.tv_market_price_value);
            tv_now_price_value=(TextView)root.findViewById(R.id.tv_now_price_value);
        }
    }
}
