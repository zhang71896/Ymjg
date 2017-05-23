package com.yrj520.pfapp.ymjg.UI.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.entity.GoodDataBean;
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
    private List<GoodDataBean> dataBeanList=new ArrayList<GoodDataBean>();
    public void clearAll(){
        dataBeanList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<GoodDataBean> list) {
        dataBeanList.clear();
        dataBeanList.addAll(list);
        notifyDataSetChanged();
    }


    public void ClickPositionListener(int position,View parent){
        GoodDataBean dataBean=getItem(position);
        PopUtil.ShowGoodSizePopWindow(dataBean,mContext, PurchaseGoodActivity.getBottomView());
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
    public GoodDataBean getItem(int position) {
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
        final GoodDataBean data = getItem(position);
        holder.tv_number.setVisibility(View.INVISIBLE);
        holder.rl_bg.setBackgroundColor(mContext.getResources().getColor(R.color.gray_e5));
        if(!StringUtils.isEmpty(data.getGoods_num())){
            int goodNum=Integer.parseInt(data.getGoods_num());
            if(goodNum>0){
                holder.rl_bg.setBackgroundColor(mContext.getResources().getColor(R.color.color_text_bg));
                holder.tv_number.setVisibility(View.VISIBLE);
                holder.tv_number.setText(data.getGoods_num());
            }
        }
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
        private RelativeLayout rl_bg;
        private ImageView iv_pic;
        private TextView good_name;
        private TextView tv_market_price_value;
        private TextView tv_now_price_value;
        private TextView tv_number;

        final View root;
        ViewHolder(View root) {
            this.root = root;
            tv_number=(TextView)root.findViewById(R.id.tv_number);
            rl_bg=(RelativeLayout)root.findViewById(R.id.rl_bg);
            iv_pic=(ImageView)root.findViewById(R.id.iv_pic);
            good_name=(TextView)root.findViewById(R.id.good_name);
            tv_market_price_value=(TextView)root.findViewById(R.id.tv_market_price_value);
            tv_now_price_value=(TextView)root.findViewById(R.id.tv_now_price_value);
        }
    }
}
